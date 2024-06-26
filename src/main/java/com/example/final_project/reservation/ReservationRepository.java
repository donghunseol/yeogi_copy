package com.example.final_project.reservation;

import com.example.final_project.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    // 날짜 범위와 방 별 예약 조회
    @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId AND NOT (r.checkOutDate < :startDate OR r.checkInDate > :endDate)")
    List<Reservation> findReservationsByDateRangeAndRoomId(@Param("roomId") Integer roomId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // 객실 별 예약 조회(달력)
    @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId")
    List<Reservation> findReservationsByRoomId(@Param("roomId") Integer roomId);

    // 유저의 예약 내역 조회 (목록)
    @Query("SELECT r FROM Reservation r JOIN FETCH r.room ro JOIN FETCH ro.stay WHERE r.user.id = :userId")
    List<Reservation> findByUserIdWithRoomAndStay(@Param("userId") Integer userId);

    // 로그인한 유저의 예약 내역 중 하나를 선택해서 조회 (상세보기)
    @Query("SELECT r FROM Reservation r JOIN FETCH r.room ro JOIN FETCH ro.stay WHERE r.id = :reservationId")
    Reservation findByReservationIdWithRoomAndStay(@Param("reservationId") Integer reservationId);

    // 기업의 숙소 예약 내역 조회 (목록)
    @Query("SELECT r FROM Reservation r JOIN FETCH r.room ro JOIN FETCH ro.stay WHERE r.room.stay.company.id = :companyId")
    List<Reservation> findByCompanyIdWithRoomAndStay(@Param("companyId") Integer companyId);

    // 유저가 한 숙소에 예약한 내역
    @Query("SELECT r FROM Reservation r JOIN FETCH r.room ro JOIN FETCH ro.stay WHERE r.room.stay.id = :stayId AND r.user.id = :userId AND r.id = :roomId")
    Reservation findByStayIdWithUserId(@Param("stayId") Integer stayId, @Param("userId") Integer userId, @Param("roomId") Integer roomId);

    @Query("SELECT r, ro, i FROM Reservation r JOIN fetch r.room ro JOIN FETCH ro.roomInformation i where r.id = :reservationId")
    Reservation findByIdWithRoomAndRoomInformation(@Param("reservationId") Integer reservationId);

    // 로그인 한 회원의 알림 목록
    @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId")
    List<Reservation> findByUserId(@Param("userId") Integer userId);

    // 키워드로 예약찾기
    @Query("""
            select r
            from Reservation r  
            JOIN FETCH r.room ro
            JOIN FETCH r.room.stay s
            where r.room.stay.name like %:keyword% or r.room.name like %:keyword% AND r.room.stay.company.id = :companyId
            """)
    List<Reservation> findAllKeyword(@Param("keyword") String keyword, @Param("companyId") Integer companyId);

}

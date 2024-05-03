package com.example.final_project.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    // 날짜 범위와 방 별 예약 조회
    @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId AND NOT (r.checkOutDate < :startDate OR r.checkInDate > :endDate)")
    Optional<List<Reservation>> findReservationsByDateRangeAndRoomId(@Param("roomId") Integer roomId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // 로그인한 유저의 예약 내역 조회 (목록)
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

}

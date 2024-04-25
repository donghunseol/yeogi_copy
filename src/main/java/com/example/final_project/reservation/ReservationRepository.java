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

    @Query("select re  from Reservation re join fetch Room r on r.id = re.room.id join fetch User u on u.id = re.user.id where u.id = :userId")
    List<Reservation> findByUserId(@Param("userId") Integer userId);

    @Query("select r from Reservation r join fetch r.user u where u.id = :id")
    List<Reservation> findByUserIdd(@Param("id") int id);

}

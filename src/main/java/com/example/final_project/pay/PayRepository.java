package com.example.final_project.pay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PayRepository extends JpaRepository<Pay, Integer> {

    @Query("select p from Pay p where p.reservation.id = :reservationId")
    Optional<Pay> findByReservationId(@Param("reservationId") Integer reservationId);

    @Query("SELECT p, r FROM Pay p JOIN FETCH Reservation r ON p.reservation.id = r.id JOIN FETCH Room ro ON r.room.id = ro.id WHERE p.reservation.room.id = :roomId AND r.checkInDate = :now")
    Pay findByRoomId(@Param("roomId") Integer roomId, @Param("now")LocalDate now);
}

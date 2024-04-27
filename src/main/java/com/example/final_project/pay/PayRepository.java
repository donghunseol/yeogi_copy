package com.example.final_project.pay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PayRepository extends JpaRepository<Pay, Integer> {

    @Query("select p from Pay p where p.reservation.id = :reservationId")
    Optional<Pay> findByReservationId(@Param("reservationId") Integer reservationId);
}

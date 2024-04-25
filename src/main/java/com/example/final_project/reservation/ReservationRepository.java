package com.example.final_project.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("select re  from Reservation re join fetch Room r on r.id = re.room.id join fetch User u on u.id = re.user.id where u.id = :userId")
    List<Reservation> findByUserId(@Param("userId") Integer userId);


    @Query("select r from Reservation r join fetch r.user u where u.id = :id")
    List<Reservation> findByUserIdd(@Param("id") int id);
}

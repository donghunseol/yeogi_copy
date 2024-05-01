package com.example.final_project.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("select r from Room r join fetch Stay s on r.stay.id = s.id  join fetch RoomInformation ri on r.roomInformation.id = ri.id where s.id = :stayId order by r.id")
    List<Room> findByStayId(@Param("stayId")Integer stayId);

}

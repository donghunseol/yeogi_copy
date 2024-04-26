package com.example.final_project.room_information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomInformationRepository extends JpaRepository<RoomInformation, Integer> {
    // 해당 객실에 대한 정보 찾기
    @Query("select i from RoomInformation i join fetch i.room r where i.room.id = :roomId")
    RoomInformation findByRoomId(@Param("roomId") Integer roomId);
}

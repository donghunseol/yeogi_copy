package com.example.final_project.room;

import com.example.final_project.company.CompanyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("select r from Room r join fetch Stay s on r.stay.id = s.id  join fetch RoomInformation ri on r.roomInformation.id = ri.id where s.id = :stayId order by r.id")
    List<Room> findByStayId(@Param("stayId") Integer stayId);

    @Query("SELECT COUNT(r) FROM Room r JOIN FETCH Stay s ON r.stay.id = s.id JOIN FETCH RoomInformation ri ON r.roomInformation.id = ri.id WHERE s.id = :stayId AND r.tier = :tier")
    Integer findByStayIdAndTier(@Param("stayId") Integer stayId, @Param("tier") String tier);

    @Query("SELECT r.tier, COUNT(r) FROM Room r WHERE r.stay.id = :stayId AND r.tier = :tier GROUP BY r.tier")
    List<CompanyResponse.roomCountByStayIdAndTierDTO> countByStayIdAndTier(@Param("stayId") Integer stayId, @Param("tier") String tier);

}

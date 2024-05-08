package com.example.final_project.stay_image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StayImageRepository extends JpaRepository<StayImage, Integer> {
    @Query("SELECT s FROM StayImage s JOIN FETCH s.stay st WHERE st.id = :stayId")
    List<StayImage> findByStayId (@Param("stayId") Integer stayId);

}

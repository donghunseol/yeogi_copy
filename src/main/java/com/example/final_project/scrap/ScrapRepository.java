package com.example.final_project.scrap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Integer> {

    // 좋아요 찾기
    @Query("SELECT s FROM Scrap s JOIN FETCH s.user JOIN FETCH s.stay WHERE s.user.id = :userId AND s.stay.id = :stayId")
    Optional<Scrap> findByuserIdWithStayId(@Param("userId") Integer userId, @Param("stayId") Integer stayId);
}

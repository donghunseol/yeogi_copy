package com.example.final_project.review;

import com.example.final_project.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // 한 개인이 작성한 리뷰 정보
    @Query("SELECT r from Review r JOIN FETCH User u ON r.user.id = u.id JOIN FETCH Room ro ON r.room.id = ro.id WHERE r.user.id = :userId")
    List<Review> findByUserIdWithUserAndRoom(@Param("userId") Integer userId);

}

package com.example.final_project.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // 한 개인이 작성한 리뷰 정보
    @Query("SELECT r, st from Review r JOIN FETCH User u ON r.writer.id = u.id JOIN FETCH Stay st ON r.stay.id = st.id WHERE r.writer.id = :userId AND r.parent IS NULL")
    List<Review> findByUserIdWithUserAndRoom(@Param("userId") Integer userId);

    // 한 숙소의 모든리뷰
    @Query("SELECT DISTINCT r FROM Review r JOIN FETCH r.writer JOIN FETCH r.stay WHERE r.stay.id = :stayId")
    List<Review> findAllByStayIdWithDetails(@Param("stayId") Integer stayId);

    // 한 숙소의 대댓글을 제외한 리뷰
    @Query("SELECT DISTINCT r FROM Review r JOIN FETCH r.writer JOIN FETCH r.stay WHERE r.stay.id = :stayId AND r.parent is null")
    List<Review> findNoParentReviewByStayIdWithDetails(@Param("stayId") Integer stayId);

    // 댓글의 부모찾기
    @Query("SELECT r from Review r LEFT JOIN FETCH r.parent WHERE r.id = :reviewId ")
    Review findReviewByIdWithParent(@Param("reviewId") Integer reviewId);

    // 댓글 디테일
    @Query("SELECT r FROM Review r JOIN FETCH r.writer JOIN FETCH r.stay WHERE r.id = :reviewId")
    Review findByReviewId(@Param("reviewId") Integer reviewId);

}

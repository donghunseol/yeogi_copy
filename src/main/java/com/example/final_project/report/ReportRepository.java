package com.example.final_project.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Query("SELECT r, re, u, s FROM Report r JOIN FETCH Review re ON r.review.id = re.id JOIN FETCH User u ON r.user.id = u.id JOIN FETCH Stay s ON re.stay.id = s.id ORDER BY r.reportId DESC")
    List<Report> findAllWithReviewAndUserAndStay();
}

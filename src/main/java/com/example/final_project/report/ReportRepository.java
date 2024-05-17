package com.example.final_project.report;

import com.example.final_project.admin.AdminResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Query("SELECT r, re, u, s FROM Report r JOIN FETCH Review re ON r.review.id = re.id JOIN FETCH User u ON r.user.id = u.id JOIN FETCH Stay s ON re.stay.id = s.id ORDER BY r.reportId DESC")
    List<Report> findAllWithReviewAndUserAndStay();

    @Query("SELECT new com.example.final_project.admin.AdminResponse$ReportDetail(r, re.children, re, u, s) FROM Report r JOIN FETCH Review re ON r.review.id = re.id JOIN FETCH User u ON r.user.id = u.id JOIN FETCH Stay s ON re.stay.id = s.id WHERE r.reportId = :reportId")
    AdminResponse.ReportDetail findByIdWithReviewAndUserAndStay(@Param("reportId") Integer reportId);

}

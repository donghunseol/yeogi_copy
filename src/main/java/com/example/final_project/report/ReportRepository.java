package com.example.final_project.report;

import com.example.final_project.admin.AdminResponse;
import com.example.final_project.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Query("SELECT r, re, u, s FROM Report r JOIN FETCH Review re ON r.review.id = re.id JOIN FETCH User u ON r.user.id = u.id JOIN FETCH Stay s ON re.stay.id = s.id ORDER BY r.reportId DESC")
    List<Report> findAllWithReviewAndUserAndStay();

//    @Query("SELECT new com.example.final_project.admin.AdminResponse$ReportDetail(r, re.children, re, u, s) FROM Report r JOIN FETCH Review re ON r.review.id = re.id JOIN FETCH User u ON r.user.id = u.id JOIN FETCH Stay s ON re.stay.id = s.id WHERE r.reportId = :reportId")
//    AdminResponse.ReportDetail findByIdWithReviewAndUserAndStay(@Param("reportId") Integer reportId);

    @Query("SELECT r, re.children, re, u, s FROM Report r JOIN FETCH Review re ON r.review.id = re.id LEFT JOIN FETCH re.children JOIN FETCH User u ON r.user.id = u.id JOIN FETCH Stay s ON re.stay.id = s.id WHERE r.reportId = :reportId")
    Report findByIdWithReviewAndUserAndStay(@Param("reportId") Integer reportId);

    @Query("SELECT r, re FROM Report r JOIN FETCH Review re ON r.review.id = re.id WHERE r.reportId = :reportId")
    Report findByIdWithReview(@Param("reportId") Integer reportId);

    // 키워드로 신고내역찾기
    @Query("""
    select r
    from Report r 
    JOIN FETCH r.stay s 
    JOIN FETCH r.user u
    where s.name like %:keyword% or u.email like %:keyword%
    """)
    List<Report> findAllKeyword(@Param("keyword") String keyword);
}

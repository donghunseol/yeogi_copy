package com.example.final_project.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    // 유저가 신고한 리뷰 리스트
    @Query("SELECT re FROM Report re " +
            "JOIN FETCH re.review r " +
            "JOIN FETCH re.reviewComment rc " +
            "JOIN FETCH re.user u " +
            "JOIN FETCH re.company c " +
            "WHERE c.id = :companyId")
    List<Report> findByCompanyId(@Param("companyId") Integer companyId);

}

package com.example.final_project.faq;

import com.example.final_project.question.Question;
import com.example.final_project.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Integer> {
    @Query("SELECT f FROM Faq f WHERE f.id = :id order by f.id desc")
    List<Faq> findAllList();

    // 공통 및 유저를 위한 FAQ 리스트
    @Query("SELECT f FROM Faq f WHERE f.classification <> 'Company' order by f.id desc")
    List<Faq> findAllByExcludeComapny();

    // keyword 검색
    @Query("""
    select f
    from Faq f 
    JOIN FETCH f.admin 
    where f.content like %:keyword% or f.admin.name like %:keyword% order by f.id desc
    """)
    List<Faq> findAllKeyword(@Param("keyword") String keyword);
}

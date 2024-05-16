package com.example.final_project.faq;

import com.example.final_project.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Integer> {

    // 공통 및 유저를 위한 FAQ 리스트
    @Query("SELECT f FROM Faq f WHERE f.classification <> 'Company'")
    List<Faq> findAllByExcludeComapny();
}

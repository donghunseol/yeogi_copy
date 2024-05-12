package com.example.final_project.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    // [기업] 문의사항 리스트
    @Query("SELECT q FROM Question q LEFT JOIN q.company c WHERE c.id IS NOT NULL")
    List<Question> companyQuestionList();

}

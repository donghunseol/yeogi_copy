package com.example.final_project.question;

import com.example.final_project._core.enums.QuestionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    // [기업] 문의사항 리스트
    @Query("SELECT q FROM Question q LEFT JOIN q.company c WHERE c.id IS NOT NULL")
    List<Question> companyQuestionList();


    // [기업] 문의사항 디테일
    @Query("SELECT q FROM Question q LEFT JOIN q.company c WHERE q.id = :questionId")
    Optional<Question> companyQuestionDetail(@Param("questionId") Integer questionId);

    @Query("SELECT q FROM Question q LEFT JOIN q.company c WHERE q.state = :inquiry AND q.content LIKE %:keyword% ORDER BY q.id DESC")
    List<Question> findAllByOption(@Param("inquiry") QuestionEnum inquiry, @Param("keyword") String keyword);


}

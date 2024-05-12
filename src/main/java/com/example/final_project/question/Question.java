package com.example.final_project.question;

import com.example.final_project._core.enums.QuestionEnum;
import com.example.final_project.company.Company;
import com.example.final_project.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "question_tb")
@Entity
@JsonIgnoreProperties({"user", "company"})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 문의사항 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 문의한 유저 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company; // 문의 대상인 기업 번호

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String content; // 내용

    @Enumerated(EnumType.STRING)
    private QuestionEnum state;  //상태 WAIT :대기중 , COMPLETION :완료

    private String answer; // 답변 (null 여부에 따라 답변 여부 판단)

    @CreationTimestamp
    private LocalDateTime createdAt; // 문의사항 작성 일자

    @Builder
    public Question(Integer id, User user, Company company, String title, String content, String answer, LocalDateTime createdAt, QuestionEnum questionEnum) {
        this.id = id;
        this.user = user;
        this.company = company;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.state = questionEnum;
        this.createdAt = createdAt;
    }
}
package com.example.final_project.report;

import com.example.final_project._core.enums.ReportEnum;
import com.example.final_project.company.Company;
import com.example.final_project.review.Review;
import com.example.final_project.review_comment.ReviewComment;
import com.example.final_project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "report_tb")
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 숙소 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review; // 리뷰 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private ReviewComment reviewComment; // 리뷰 댓글 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 유저 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company; // 기업 번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportEnum result; // Proceeding 신고 진행 중, Complete 신고 완료, Fail 신고 실패

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public Report(Integer id, Review review, User user, Company company, ReportEnum result, LocalDateTime createdAt) {
        this.id = id;
        this.review = review;
        this.user = user;
        this.company = company;
        this.result = result;
        this.createdAt = createdAt;
    }
}
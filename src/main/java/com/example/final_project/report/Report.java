package com.example.final_project.report;

import com.example.final_project._core.enums.ReportEnum;
import com.example.final_project.company.Company;
import com.example.final_project.review.Review;
import com.example.final_project.stay.Stay;
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
@Table(name = "report_tb")
@Entity
//@JsonIgnoreProperties({"review", "company", "stay"})
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId; // 신고 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review; // 리뷰

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 유저 번호

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id")
//    private Company company; // 신고한 기업

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stay_id")
    private Stay stay; // 숙소 번호

    @Enumerated(EnumType.STRING)
//  @Column(nullable = false)
    private ReportEnum result; // Proceeding 신고 진행 중, Complete 신고 완료, Fail 신고 실패

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Column(nullable = false)
    private String reportContent; // 신고 내용


    @Builder
    public Report(Integer reportId, Review review, User user, ReportEnum result, LocalDateTime createdAt, String reportContent,Stay stay) {
        this.reportId = reportId;
        this.stay = stay;
        this.review = review;
        this.user = user;
//        this.company = company;
        this.result = result;
        this.createdAt = createdAt;
        this.reportContent = reportContent;
    }
}
package com.example.final_project.review_comment;

import com.example.final_project.company.Company;
import com.example.final_project.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "review_comment_tb")
@Entity
@JsonIgnoreProperties({"company"})
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 리뷰 댓글 번호

    @OneToOne(fetch = FetchType.LAZY, optional = false) // optional = false를 적어야 Not Null이 된다.
    private Review review; // 리뷰 번호

    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    private Company company; // 기업 번호

    @Column(nullable = false)
    private String content; // 내용

    @Column(nullable = false)
    private Boolean isDelete; // 삭제 여부(true: 삭제 됨, false: 삭제 되지 않음)

    @Column(nullable = false)
    private LocalDateTime createdAt; // 댓글 생성 일자

    @Builder
    public ReviewComment(Integer id, Review review, Company company, String content, Boolean isDelete, LocalDateTime createdAt) {
        this.id = id;
        this.review = review;
        this.company = company;
        this.content = content;
        this.isDelete = isDelete;
        this.createdAt = createdAt;
    }
}

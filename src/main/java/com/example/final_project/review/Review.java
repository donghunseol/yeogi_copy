package com.example.final_project.review;

import com.example.final_project._core.enums.ReviewEnum;
import com.example.final_project.company.Company;
import com.example.final_project.stay.Stay;
import com.example.final_project.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "review_tb")
@Entity
@JsonIgnoreProperties({"user", "company", "stay", "children"})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 리뷰 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 리뷰쓴 유저

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company; // 리뷰를 작성한 기업

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stay_id")
    private Stay stay; // 리뷰한 숙소

    private Integer score; // 평점

    @Column(nullable = false)
    private String content; // 내용

    @Enumerated(EnumType.STRING)
    private ReviewEnum state; // FLAWLESS : 기본(문제 없는 리뷰), REPORTED : 신고 된 리뷰, DELETED : 삭제한 리뷰

    @CreationTimestamp
    private LocalDateTime createdAt; // 리뷰 작성 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Review parent; // 부모댓글

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Review> children = new ArrayList<>(); //자식댓글 (대댓글)

    @Builder
    public Review(Integer id, User user, Company company, Stay stay, Integer score, String content, ReviewEnum state, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.company = company;
        this.stay = stay;
        this.score = score;
        this.content = content;
        this.state = state;
        this.createdAt = createdAt;
    }

    public Review(String content) {
        this.content = content;
    }

    public void updateWriter(User user) {
        this.user = user;
    }

    public void updateBoard(Stay stay) {
        this.stay = stay;
    }

    public void updateParent(Review review) {
        this.parent = review;
    }

    public void setStateDeleted(ReviewEnum state) {
        if (state == ReviewEnum.FLAWLESS){
            this.state = ReviewEnum.DELETED;
        }

    }
    public void setStateReported(ReviewEnum state) {
        if (state == ReviewEnum.FLAWLESS){
            this.state = ReviewEnum.REPORTED;
        }
    }


}
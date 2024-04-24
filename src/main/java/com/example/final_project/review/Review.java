package com.example.final_project.review;

import com.example.final_project._core.enums.ReviewEnum;
import com.example.final_project.room.Room;
import com.example.final_project.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "review_tb")
@Entity
@JsonIgnoreProperties({"user", "room"})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 리뷰 번호

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // optional = false를 적어야 Not Null이 된다.
    private User user; // 리뷰 남긴 유저 번호

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Room room; // 리뷰한 객실 번호

    @Column(nullable = false)
    private Integer score; // 평점

    @Column(nullable = false)
    private String content; // 내용

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewEnum isDelete; // 삭제 여부(FLAWLESS : 문제 없는 댓글, COMPLETE: 삭제 됨, FAIL: 삭제 안 됨)

    @Column(nullable = false)
    private LocalDateTime createdAt; // 리뷰 작성 날짜

    @Builder
    public Review(Integer id, User user, Room room, Integer score, String content, ReviewEnum isDelete, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.score = score;
        this.content = content;
        this.isDelete = isDelete;
        this.createdAt = createdAt;
    }
}
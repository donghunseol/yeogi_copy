package com.example.final_project.stay_image;


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
@Table(name = "scrap_tb")
@Entity
@JsonIgnoreProperties({"user", "stay"})
public class StayImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 스크랩 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 스크랩한 유저 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Stay stay; // 스크랩된 숙소 번호

    @CreationTimestamp
    private LocalDateTime createdAt; // 스크랩 생성 일자

    @Builder
    public StayImage(Integer id, User user, Stay stay, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.stay = stay;
        this.createdAt = createdAt;
    }
}
package com.example.final_project.stay_image;


import com.example.final_project.stay.Stay;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "stay_image_tb")
@Entity
@JsonIgnoreProperties({"stay"})
public class StayImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 숙소 이미지 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Stay stay; // 이미지가 등록된 숙소 번호

    @Column(nullable = false)
    private String name; // 이미지 이름

    @Column(nullable = false)
    private String path; // 이미지 경로

    @CreationTimestamp
    private LocalDateTime createdAt; // 이미지 생성 일자

    @Builder
    public StayImage(Integer id, Stay stay, String name, String path, LocalDateTime createdAt) {
        this.id = id;
        this.stay = stay;
        this.name = name;
        this.path = path;
        this.createdAt = createdAt;
    }
}
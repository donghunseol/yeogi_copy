package com.example.final_project.room;

import com.example.final_project.stay.Stay;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "rooms_tb")
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 객실 옵션 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Stay stay; // 객실에 해상하는 숙소

    @Column(nullable = false)
    private String name; // 객실 이름

    @Column(nullable = false)
    private String tier; // 객실 등급

    @Column(nullable = false)
    private Integer price; // 객실 가격

    private Integer specialPrice; // 특가

    @Column(nullable = false)
    private Boolean specialState; // 특가 적용 여부(true: 특가 적용 함, false: 특가 적용 안 함)

    @Column(nullable = false)
    private Integer count; // 잔여 객실 수

    @Column(nullable = false)
    private String information; // 객실 이용 정보

    private String imageName; // 이미지 파일명

    private String imagePath; // 이미지 경로명

    @Column(nullable = false)
    private LocalDateTime createdAt; // 객실 등록 날짜

    @Builder
    public Room(Integer id, Stay stay, String name, String tier, Integer price, Integer specialPrice, Boolean specialState, Integer count, String information, String imageName, String imagePath, LocalDateTime createdAt) {
        this.id = id;
        this.stay = stay;
        this.name = name;
        this.tier = tier;
        this.price = price;
        this.specialPrice = specialPrice;
        this.specialState = specialState;
        this.count = count;
        this.information = information;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
    }
}
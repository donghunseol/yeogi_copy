package com.example.final_project.room;

import com.example.final_project._core.enums.RoomEnum;
import com.example.final_project.stay.Stay;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "room_tb")
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
    private String roomNumber; // 호실

    @Column(nullable = false)
    private Integer price; // 객실 가격

    private Integer specialPrice; // 특가

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomEnum specialState; // 특가 적용 여부(APPLIED: 특가 적용 함, NOT_APPLIED: 특가 적용 안 함)

    private String imageName; // 이미지 파일명

    private String imagePath; // 이미지 경로명

    @Column(nullable = false)
    private LocalDateTime createdAt; // 객실 등록 날짜

    @Builder
    public Room(Integer id, Stay stay, String name, String tier, String roomNumber, Integer price, Integer specialPrice, RoomEnum specialState, String imageName, String imagePath, LocalDateTime createdAt) {
        this.id = id;
        this.stay = stay;
        this.name = name;
        this.tier = tier;
        this.roomNumber = roomNumber;
        this.price = price;
        this.specialPrice = specialPrice;
        this.specialState = specialState;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
    }
}
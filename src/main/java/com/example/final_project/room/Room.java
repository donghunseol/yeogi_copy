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
<<<<<<< HEAD
    private Boolean specialState; // 특가 적용 여부(true: 특가 적용 함, false: 특가 적용 안 함)

    @Column(nullable = false)
    private Integer count; // 잔여 객실 수

    @Column(nullable = false)
    private String information; // 객실 이용 정보
=======
    private RoomEnum specialState; // 특가 적용 여부(APPLIED: 특가 적용 함, NOT_APPLIED: 특가 적용 안 함)
>>>>>>> eb2afd1a1cf5a2c8dfd2918925ed1d6cc319492b

    private String imageName; // 이미지 파일명

    private String imagePath; // 이미지 경로명

    @Column(nullable = false)
    private LocalDateTime createdAt; // 객실 등록 날짜

    @Builder
<<<<<<< HEAD
    public Room(Integer id, Stay stay, String name, String tier, Integer price, Integer specialPrice, Boolean specialState, Integer count, String information, String imageName, String imagePath, LocalDateTime createdAt) {
=======
    public Room(Integer id, Stay stay, String name, String tier, String roomNumber, Integer price, Integer specialPrice, RoomEnum specialState, String imageName, String imagePath, LocalDateTime createdAt) {
>>>>>>> eb2afd1a1cf5a2c8dfd2918925ed1d6cc319492b
        this.id = id;
        this.stay = stay;
        this.name = name;
        this.tier = tier;
        this.roomNumber = roomNumber;
        this.price = price;
        this.specialPrice = specialPrice;
        this.specialState = specialState;
<<<<<<< HEAD
        this.count = count;
        this.information = information;
=======
>>>>>>> eb2afd1a1cf5a2c8dfd2918925ed1d6cc319492b
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
    }
}
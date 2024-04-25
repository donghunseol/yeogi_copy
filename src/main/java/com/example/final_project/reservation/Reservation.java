package com.example.final_project.reservation;


import com.example.final_project.room.Room;
import com.example.final_project.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "reservation_tb")
@Entity
@JsonIgnoreProperties({"user", "room"})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 예약 번호

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // optional = false를 적어야 Not Null이 된다.
    private User user; // 예약한 유저 번호

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Room room; // 예약한 객실 번호

    @Column(nullable = false)
    private String reservationName; // 예약자 대표 이름

    @Column(nullable = false)
    private String reservationTel; // 예약자 대표 전화번호

    @Column(nullable = false)
    private LocalDate checkInDate; // 입실 날짜

    @Column(nullable = false)
    private LocalDate checkOutDate; // 퇴실 날짜

    @Column(nullable = false)
    private LocalDateTime createdAt; // 예약완료된 시간

    @Builder
    public Reservation(Integer id, User user, Room room, LocalDate checkInDate, LocalDate checkOutDate, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.createdAt = createdAt;
    }
}
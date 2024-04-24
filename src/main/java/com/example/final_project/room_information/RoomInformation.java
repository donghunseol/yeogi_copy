package com.example.final_project.room_information;

import com.example.final_project.room.Room;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@Data
@Table(name = "room_information_tb")
@Entity
public class RoomInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 방 이용 정보 번호

    @OneToOne(fetch = FetchType.LAZY, optional = false) // optional = false를 적어야 Not Null이 된다.
    private Room room;

    @Column(nullable = false)
    private LocalTime checkIn; // 입실 시간

    @Column(nullable = false)
    private LocalTime checkOut; // 퇴실 시간

    @Column(nullable = false)
    private Integer minPerson; // 최소 인원

    @Column(nullable = false)
    private Integer maxPerson; // 최대 인원

    private String moreInfo; // 추가 정보 (ex. 조식 제공)

    @Column(nullable = false)
    private LocalDateTime createdAt; // 방 이용 정보 생성 일자

    @Builder
    public RoomInformation(Integer id, Room room, LocalTime checkIn, LocalTime checkOut, Integer minPerson, Integer maxPerson, String moreInfo, LocalDateTime createdAt) {
        this.id = id;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.minPerson = minPerson;
        this.maxPerson = maxPerson;
        this.moreInfo = moreInfo;
        this.createdAt = createdAt;
    }
}
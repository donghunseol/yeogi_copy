package com.example.final_project.event;

import com.example.final_project._core.enums.EventEnum;
import com.example.final_project.admin.Admin;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Table(name = "event_tb")
@Data
@Entity
public class Event {
    // 이벤트 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 이벤트 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin; // 관리자 번호

    @Column(nullable = false)
    private String name; // 이벤트 이름

    // @Column(nullable = false) // 나중에 false로 수정
    private String imageName; // 이벤트 이미지

    // @Column(nullable = false) // 나중에 false로 수정
    private String imagePath; // 이벤트 이미지 경로

    @Column(nullable = false)
    private LocalDate start; // 이벤트 시작일

    @Column(nullable = false)
    private LocalDate end; // 이벤트 종료일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventEnum state; // 이벤트 상태 / Enable 진행 중, Disable 종료

    @Column(nullable = false)
    private String createdAt; // 작성일

    @Builder
    public Event(Integer id, Admin admin, String name, String imageName, String imagePath, LocalDate start, LocalDate end, EventEnum state, String createdAt) {
        this.id = id;
        this.admin = admin;
        this.name = name;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.start = start;
        this.end = end;
        this.state = state;
        this.createdAt = createdAt;
    }
}

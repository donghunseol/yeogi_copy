package com.example.final_project.event;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "event_tb")
@Data
@Entity
public class Event {
    // 이벤트 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 이벤트 번호
}

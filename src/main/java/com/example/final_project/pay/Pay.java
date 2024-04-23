package com.example.final_project.pay;

import com.example.final_project.room.Room;
import com.example.final_project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "pay_tb")
@Entity
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 숙소 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 숙소를 결제한 유저

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room; // 결제를 진행한 방

    @Column(nullable = false)
    private Integer amount; // 결제 금액

    @Column(nullable = false)
    private String way; // 결제 방식

    @Column(nullable = false)
    private Boolean state; // 결제 유무 / 현장 결제 카카오결제 토스결제 카드 결제

    @CreationTimestamp
    private LocalDateTime createdAt; // 결제 일자

    @Builder
    public Pay(Integer id, User user, Room room, Integer amount, String way, Boolean state, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.amount = amount;
        this.way = way;
        this.state = state;
        this.createdAt = createdAt;
    }
}
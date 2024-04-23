package com.example.final_project.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 유저 번호

    @Column(nullable = false, unique = true)
    private String email; // 이메일 (로그인 할 때 아이디로 사용)

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String name; // 회원 이름

    @Column(nullable = false, length = 11)
    private String phone; // 전화번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserState state; // 상태 (ACTIVE : 회원 유지, QUIT : 회원 탈퇴, BLACK : 신고 받아서 제한된 회원)

    enum UserState {
        ACTIVE, QUIT, BLACK // 상태 종류
    }

    @Column(nullable = false)
    private LocalDateTime birth; // 생년월일

    @Column(nullable = false)
    private Integer reportCount = 0; // 신고 받은 횟수

    @Column(nullable = false)
    private LocalDateTime createdAt; // 유저 가입 일자

    @Builder
    public User(Integer id, String email, String password, String name, String phone, UserState state, LocalDateTime birth, Integer reportCount, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.birth = birth;
        this.reportCount = reportCount;
        this.createdAt = createdAt;
    }
}



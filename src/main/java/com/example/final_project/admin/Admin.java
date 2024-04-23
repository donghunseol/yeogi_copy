package com.example.final_project.admin;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Table(name = "admin_tb")
@Data
@Entity
public class Admin {
    // 관리자 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 관리자 번호

    @Column(nullable = false)
    private String name; // 로그인 아이디

    @Column(nullable = false)
    private String password; // 비밀번호

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public Admin(Integer id, String name, String password, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
    }
}

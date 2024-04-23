package com.example.final_project.admin;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionAdmin {
    private Integer id; // 관리자 번호
    private String name; // 로그인 아이디
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public SessionAdmin(Integer id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public SessionAdmin(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.createdAt = admin.getCreatedAt();
    }

}

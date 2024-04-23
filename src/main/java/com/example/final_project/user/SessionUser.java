package com.example.final_project.user;

import com.example.final_project._core.enums.UserEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SessionUser {
    private Integer id; // 유저 번호
    private String email; // 이메일 (로그인 할 때 아이디로 사용)
    private String name; // 회원 이름
    private String phone; // 전화번호
    private UserEnum state; // 상태 (ACTIVE : 회원 유지, QUIT : 회원 탈퇴, BLACK : 신고 받아서 제한된 회원)
    private LocalDate birth; // 생년월일
    private LocalDateTime createdAt; // 유저 가입 일자

    @Builder
    public SessionUser(Integer id, String email, String name, String phone, UserEnum state, LocalDate birth, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.birth = birth;
        this.createdAt = createdAt;
    }

    public SessionUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.state = user.getState();
        this.birth = user.getBirth();
        this.createdAt = user.getCreatedAt();
    }
}

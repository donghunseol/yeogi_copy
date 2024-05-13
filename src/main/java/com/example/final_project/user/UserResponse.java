package com.example.final_project.user;

import com.example.final_project._core.enums.UserEnum;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserResponse {

    // 회원 정보 수정
    @Data
    public static class UpdateDTO {
        private Integer id;
        private String email;
        private String name;
        private String phone;
        private LocalDate birth;

        public UpdateDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.phone = user.getPhone();
            this.birth = user.getBirth();
        }
    }

    // 로그인 DTO
    @Data
    public static class LoginDTO {
        private Integer id; // 유저 번호
        private String email; // 이메일 (로그인 할 때 아이디로 사용)
        private String name; // 회원 이름
        private String phone; // 전화번호
        private UserEnum state; // 상태 (ACTIVE : 회원 유지, QUIT : 회원 탈퇴, BLACK : 신고 받아서 제한된 회원)
        private LocalDate birth; // 생년월일
        private LocalDateTime createdAt; // 유저 가입 일자

        public LoginDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.phone = user.getPhone();
            this.state = user.getState();
            this.birth = user.getBirth();
            this.createdAt = user.getCreatedAt();
        }
    }

    @Data
    public static class JoinDTO {
        private Integer id; // 유저 번호
        private String email; // 이메일 (로그인 할 때 아이디로 사용)
        private String name; // 회원 이름
        private String phone; // 전화번호
        private UserEnum state; // 상태 (ACTIVE : 회원 유지, QUIT : 회원 탈퇴, BLACK : 신고 받아서 제한된 회원)
        private LocalDate birth; // 생년월일
        private LocalDateTime createdAt; // 유저 가입 일자

        public JoinDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.phone = user.getPhone();
            this.state = user.getState();
            this.birth = user.getBirth();
            this.createdAt = user.getCreatedAt();
        }
    }

//    // 로그인 한 회원의 알림 목록
//    @Data
//    public static class Notifications{
//        private Integer reservationId;
//        private String
//
//    }
}

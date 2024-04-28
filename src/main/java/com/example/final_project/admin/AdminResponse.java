package com.example.final_project.admin;

import com.example.final_project._core.enums.UserEnum;
import com.example.final_project.user.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdminResponse {

    // 관리자 페이지에서 출력할 유저 정보
    @Data
    public static class userListDTO{
        private Integer id; // 유저 번호
        private String email; // 이메일 (로그인 할 때 아이디로 사용)
        private String name; // 회원 이름
        private String phone; // 전화번호
        private UserEnum state; // 상태 (ACTIVE : 회원 유지, QUIT : 회원 탈퇴, BLACK : 신고 받아서 제한된 회원)
        private LocalDate birth; // 생년월일
        private Integer reportCount; // 신고 받은 횟수
        private LocalDateTime createdAt; // 유저 가입 일자

        public userListDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.phone = user.getPhone();
            this.state = user.getState();
            this.birth = user.getBirth();
            this.reportCount = user.getReportCount();
            this.createdAt = user.getCreatedAt();
        }
    }
}

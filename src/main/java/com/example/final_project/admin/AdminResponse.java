package com.example.final_project.admin;

import com.example.final_project._core.enums.CompanyEnum;
import com.example.final_project._core.enums.UserEnum;
import com.example.final_project.company.Company;
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


    // 관리자 페이지에서 출력할 기업 정보
    @Data
    public static class companyListDTO{
        private Integer id; // 기업 번호
        private String email; // 이메일 (로그인 할 때 아이디로 사용)
        private String businessName; // 등록 상호명
        private String businessNumber; // 사업자 번호
        private String businessAddress; // 사업자 주소
        private String phone; // 사업자 전화번호
        private String name; // 사업자 이름
        private CompanyEnum state; // 상태 (ACTIVE : 기업 유지, QUIT : 기업 탈퇴, BLACK : 신고 받아서 제한된 기업)
        private Integer reportCount; // 신고 받은 횟수
        private LocalDateTime createdAt; // 기업 가입 일자

        public companyListDTO(Company company) {
            this.id = company.getId();
            this.email = company.getEmail();
            this.businessName = company.getBusinessName();
            this.businessNumber = company.getBusinessNumber();
            this.businessAddress = company.getBusinessAddress();
            this.phone = company.getPhone();
            this.name = company.getName();
            this.state = company.getState();
            this.reportCount = company.getReportCount();
            this.createdAt = company.getCreatedAt();
        }
    }
}

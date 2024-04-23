package com.example.final_project.company;

import com.example.final_project._core.enums.CompanyEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionCompany {
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

    @Builder
    public SessionCompany(Integer id, String email, String businessName, String businessNumber, String businessAddress, String phone, String name, CompanyEnum state, Integer reportCount, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.businessName = businessName;
        this.businessNumber = businessNumber;
        this.businessAddress = businessAddress;
        this.phone = phone;
        this.name = name;
        this.state = state;
        this.reportCount = reportCount;
        this.createdAt = createdAt;
    }

    public SessionCompany(Company company) {
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

package com.example.final_project.company;

import com.example.final_project._core.enums.CompanyEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "company_tb")
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 기업 번호

    @Column(nullable = false, unique = true)
    private String email; // 이메일 (로그인 할 때 아이디로 사용)

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String businessName; // 등록 상호명

    @Column(nullable = false, length = 12)
    private String businessNumber; // 사업자 번호

    @Column(nullable = false)
    private String businessAddress; // 사업자 주소

    @Column(nullable = false)
    private String phone; // 사업자 전화번호

    @Column(nullable = false)
    private String name; // 사업자 이름

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanyEnum state; // 상태 (ACTIVE : 기업 유지, QUIT : 기업 탈퇴, BLACK : 신고 받아서 제한된 기업)

    @Column(nullable = false)
    private Integer reportCount; // 신고 받은 횟수

    @CreationTimestamp
    private LocalDateTime createdAt; // 기업 가입 일자

    @Builder
    public Company(Integer id, String email, String password, String businessName, String businessNumber, String businessAddress, String phone, String name, LocalDateTime createdAt, CompanyEnum state, Integer reportCount) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.businessName = businessName;
        this.businessNumber = businessNumber;
        this.businessAddress = businessAddress;
        this.phone = phone;
        this.name = name;
        this.state = state;
        this.reportCount = reportCount;
        this.createdAt = createdAt;
    }

    public Company(Company company) {
        this.id = company.getId();
        this.email = company.getEmail();
        this.password = company.getPassword();
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

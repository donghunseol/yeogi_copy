package com.example.final_project.faq;

import com.example.final_project._core.enums.FaqEnum;
import com.example.final_project.admin.Admin;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "faq_tb")
@Entity
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 자주 묻는 질문 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin; // 관리자 번호

    @Column(nullable = false)
    private String content; // 질문 내용

    @Column(nullable = false)
    private String reply; // 답변 내용

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자


    @Enumerated(EnumType.STRING)
    private FaqEnum classification; // TODO 관리자가 FAQ 작성시 리스트 뿌릴때 분류해서 뿌리려고 추가해놨습니다.

    @Builder
    public Faq(Integer id, Admin admin, String content, String reply, LocalDateTime createdAt, FaqEnum classification) {
        this.id = id;
        this.admin = admin;
        this.content = content;
        this.reply = reply;
        this.createdAt = createdAt;
        this.classification = classification;
    }
}
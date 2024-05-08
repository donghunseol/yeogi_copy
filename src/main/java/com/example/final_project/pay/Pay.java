package com.example.final_project.pay;

import com.example.final_project._core.enums.PayEnum;
import com.example.final_project._core.utils.DateUtil;
import com.example.final_project.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "pay_tb")
@Entity
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 결제 번호

    @OneToOne(fetch = FetchType.LAZY, optional = false) // optional = false를 적어야 Not Null이 된다.
    private Reservation reservation; // 예약 번호

    @Column(nullable = false)
    private Integer amount; // 결제 금액

    @Column(nullable = false)
    private String way; // 결제 방식 (ex. Credit Card, Debit Card, Bank Transfer, Mobile Payment ...), 결제 안됨 (No payment)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayEnum state; // PROCESSING : 처리 중, COMPLETION : 완료, CANCELLATION : 취소, REFUND : 환불

    private LocalDateTime refundDate; // 결제 환불 및 예약 취소 일자

    @CreationTimestamp
    private LocalDateTime createdAt; // 결제 일자

    @Builder
    public Pay(Integer id, Reservation reservation, Integer amount, String way, PayEnum state, LocalDateTime createdAt) {
        this.id = id;
        this.reservation = reservation;
        this.amount = amount;
        this.way = way;
        this.state = state;
        this.createdAt = createdAt;
    }

    // 결제 진행 시 가격 결제
    public void updatePay(PayRequest.DTO reqDTO) {
        this.way = reqDTO.getWay();
        this.state = reqDTO.getState();

        // 예약 일자 계산
        Integer dateCount = DateUtil.getDateCount(reservation);

        // 예약 일자를 계산 하고 가격을 곱하여 결제
        this.amount = reqDTO.getAmount() * dateCount;
    }
}
package com.example.final_project.pay;

import com.example.final_project._core.enums.PayEnum;
import lombok.Data;

import java.time.LocalDateTime;

public class PayResponse {

    // 기업 총 수익 DTO
    @Data
    public static class TotalIncomeDTO {
        private Integer companyId; // 숙소 소유 기업 번호
        private Long totalIncome; // 총 수익
        private Long reservationCount; // 수익이 들어온 예약 수

        public TotalIncomeDTO(Integer companyId, Long totalIncome, Long reservationCount) {
            this.companyId = companyId;
            this.totalIncome = totalIncome;
            this.reservationCount = reservationCount;
        }
    }

    // 숙소 총 수익 DTO
    @Data
    public static class StayTotalIncomeDTO {
        private Integer companyId; // 숙소 소유 기업 번호
        private Integer stayId; // 숙소 번호
        private Long totalIncome; // 총 수익
        private Long reservationCount; // 수익이 들어온 예약 수

        public StayTotalIncomeDTO(Integer companyId, Integer stayId, Long totalIncome, Long reservationCount) {
            this.companyId = companyId;
            this.stayId = stayId;
            this.totalIncome = totalIncome;
            this.reservationCount = reservationCount;
        }
    }

    // 결제 대표 DTO
    @Data
    public static class DTO {
        private Integer id; // 결제 번호
        private Integer reservationId; // 예약 번호
        private Integer amount; // 결제 금액
        private String way; // 결제 수단
        private PayEnum state; // 결제 유무
        private LocalDateTime createdAt; // 결제 일자

        public DTO(Pay pay) {
            this.id = pay.getId();
            this.reservationId = pay.getReservation().getId();
            this.amount = pay.getAmount();
            this.way = pay.getWay();
            this.state = pay.getState();
            this.createdAt = pay.getCreatedAt();
        }
    }

    // 예약 및 결제 취소(환불)
    @Data
    public static class RefundAndCancelReservationDTO {
        private Integer id; // 결제 번호
        private Integer reservationId; // 예약 번호
        private PayEnum state; // 결제 유무

        public RefundAndCancelReservationDTO(Pay pay) {
            this.id = pay.getId();
            this.reservationId = pay.getReservation().getId();
            this.state = pay.getState();
        }
    }
}

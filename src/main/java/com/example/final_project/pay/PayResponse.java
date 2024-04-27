package com.example.final_project.pay;

import com.example.final_project._core.enums.PayEnum;
import lombok.Data;

import java.time.LocalDateTime;

public class PayResponse {

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

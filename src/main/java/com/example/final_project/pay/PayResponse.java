package com.example.final_project.pay;

import com.example.final_project._core.enums.PayEnum;
import lombok.Data;

public class PayResponse {

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

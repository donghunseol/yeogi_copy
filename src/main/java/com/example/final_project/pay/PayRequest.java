package com.example.final_project.pay;

import com.example.final_project._core.enums.PayEnum;
import lombok.Data;

public class PayRequest {

    // 예약 후 결제 진행 DTO
    @Data
    public static class DTO {
        private Integer amount; // 결제 금액
        private String way; // 결제 방식 (ex. Credit Card, Debit Card, Bank Transfer, Mobile Payment ...), 결제 안됨 (No payment)
        private PayEnum state; // 결제 상태 (PROCESSING : 처리 중, COMPLETION : 완료, CANCELLATION : 취소, REFUND : 환불)

        public Pay toEntity() {
            return Pay.builder()
                    .amount(this.amount)
                    .way(this.way)
                    .state(this.state)
                    .build();
        }
    }
}

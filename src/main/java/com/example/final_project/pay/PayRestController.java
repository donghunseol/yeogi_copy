package com.example.final_project.pay;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PayRestController {
    private final PayService payService;
    private final HttpSession session;

    // 결제 환불 및 예약 취소
    @PutMapping("/api/reservation/refund/{payId}")
    public ResponseEntity<?> cancel(@PathVariable Integer payId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        PayResponse.RefundAndCancelReservationDTO respDTO = payService.refundAndCancel(payId, sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 결제 진행 (예약 시 결제 데이터 생성 / 이로인해 Put 으로 데이터 수정)
    @PutMapping("/api/reservation/pay/{payId}")
    public ResponseEntity<?> progress(@PathVariable Integer payId, @RequestBody PayRequest.DTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        PayResponse.DTO respDTO = payService.modifyPay(reqDTO, sessionUser, payId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}

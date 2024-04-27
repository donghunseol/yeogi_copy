package com.example.final_project.pay;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@Controller
public class PayController {
    private final PayService payService;
    private final HttpSession session;

    // 결제 환불 및 예약 취소
    @PutMapping("/book/refund/{payId}")
    public ResponseEntity<?> cancel(@PathVariable Integer payId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        PayResponse.RefundAndCancelReservationDTO respDTO = payService.refundAndCancel(payId, sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}

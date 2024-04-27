package com.example.final_project.pay;

import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.reservation.Reservation;
import com.example.final_project.reservation.ReservationRepository;
import com.example.final_project.user.SessionUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.example.final_project._core.enums.PayEnum.REFUND;

@RequiredArgsConstructor
@Service
public class PayService {

    private final PayRepository payRepository;
    private final ReservationRepository reservationRepository;

    // 결제 환불 및 예약 취소
    @Transactional
    public PayResponse.RefundAndCancelReservationDTO refundAndCancel(Integer payId, SessionUser sessionUser) {
        Pay pay = payRepository.findById(payId)
                .orElseThrow(() -> new Exception404("존재하지 않는 결제 내역입니다"));
        Reservation reservation = reservationRepository.findById(pay.getReservation().getId())
                .orElseThrow(() -> new Exception404("존재 하지 않는 예약입니다"));

        if (pay.getReservation().getUser().getId() != sessionUser.getId()) {
            // 예약을 한 유저가 아닌 경우
            throw new Exception401("예약을 취소할 권한이 없습니다");
        } else if (pay.getState() == REFUND) {
            // 이미 환불이 된 경우
            throw new Exception401("이미 취소된 예약입니다");
        } else if (reservation.getCheckOutDate().isBefore(LocalDate.now())) {
            // 이미 다녀온 경우
            throw new Exception401("이미 이용한 숙소입니다");
        }

        pay.setState(REFUND);
        pay.setRefundDate(LocalDateTime.now());

        return new PayResponse.RefundAndCancelReservationDTO(pay);
    }
}

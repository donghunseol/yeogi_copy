package com.example.final_project.reservation;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final ReservationService reservationService;
    private final HttpSession session;

    // 예약 하기 페이지
    @PostMapping("/book/{roomId}")
    public ResponseEntity<?> save(@PathVariable Integer roomId, @RequestBody ReservationRequest.DTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ReservationResponse.DTO respDTO = reservationService.save(reqDTO, sessionUser, roomId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 예약 수정 페이지
    @PutMapping("/book/{reservationId}")
    public ResponseEntity<?> update(@PathVariable Integer reservationId, @RequestBody ReservationRequest.UpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ReservationResponse.DTO respDTO = reservationService.update(reqDTO, sessionUser, reservationId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 예약 취소
    @PutMapping("/book/{reservationId}/{payId}")
    public ResponseEntity<?> cancel(@PathVariable Integer reservationId, @PathVariable Integer payId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");


        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}

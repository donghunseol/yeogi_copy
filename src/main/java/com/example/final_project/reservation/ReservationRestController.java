package com.example.final_project.reservation;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReservationRestController {
    private final ReservationService reservationService;
    private final HttpSession session;

    // 예약 하기
    @PostMapping("/api/reservation/{roomId}")
    public ResponseEntity<?> makeReservation(@PathVariable Integer roomId, @Valid @RequestBody ReservationRequest.DTO reqDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ReservationResponse.SaveDTO respDTO = reservationService.makeReservation(reqDTO, sessionUser, roomId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 예약 수정
    @PutMapping("/api/reservation/{reservationId}")
    public ResponseEntity<?> modifyReservation(@PathVariable Integer reservationId, @Valid @RequestBody ReservationRequest.UpdateDTO reqDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ReservationResponse.DTO respDTO = reservationService.modifyReservation(reqDTO, sessionUser, reservationId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}

package com.example.final_project.reservation;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final ReservationService reservationService;
    private final HttpSession session;

    // 예약 하기 페이지
    @PostMapping("/reservation/{roomId}")
    public ResponseEntity<?> save(@PathVariable Integer roomId, @RequestBody ReservationRequest.DTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ReservationResponse.DTO respDTO = reservationService.save(reqDTO, sessionUser, roomId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}

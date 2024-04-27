package com.example.final_project.user;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.reservation.ReservationResponse;
import com.example.final_project.reservation.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final ReservationService reservationService;
    private final HttpSession session;

    // 예약 내역 페이지 - 목록
    @GetMapping("/my-reservations")
    public List<ReservationResponse.ListDTO> reservationList() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ReservationResponse.ListDTO> respDTO = reservationService.reservationList(sessionUser);
        return respDTO;
    }


}

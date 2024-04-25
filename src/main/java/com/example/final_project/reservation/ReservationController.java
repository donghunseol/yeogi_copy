package com.example.final_project.reservation;

import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final ReservationService reservationService;
    private final HttpSession session;

    // 예약자 정보 view
    @PostMapping()
    public String reservationPerson() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        return "";
    }
}

package com.example.final_project.reservation;

import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
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

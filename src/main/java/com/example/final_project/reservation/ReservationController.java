package com.example.final_project.reservation;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ReservationController {
    private final ReservationService reservationService;
    private final HttpSession session;
}

package com.example.final_project.company;

import com.example.final_project.reservation.ReservationResponse;
import com.example.final_project.reservation.ReservationService;
import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CompanyController {
    private final CompanyService companyService;
    private final ReservationService reservationService;
    private final HttpSession session;

    // 예약 현황 확인 (목록)
    @GetMapping("/check-reservations-status")
    public List<ReservationResponse.ListDTO> compReservationList() {
        SessionCompany sessionCompany = (SessionCompany) session.getAttribute("sessionCompany");
        List<ReservationResponse.ListDTO> respDTO = reservationService.compReservationList(sessionCompany);
        return respDTO;
    }
}

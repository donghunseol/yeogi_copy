package com.example.final_project.company;

import com.example.final_project.reservation.ReservationResponse;
import com.example.final_project.reservation.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.example.final_project._core.enums.CompanyEnum.ACTIVE;
import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Controller
public class CompanyController {
    private final CompanyService companyService;
    private final ReservationService reservationService;
    private final HttpSession session;

    // 예약 현황 확인 (목록)
    @GetMapping("/reservations/status")
    public List<ReservationResponse.ListDTO> compReservationList() {
        SessionCompany sessionCompany = (SessionCompany) session.getAttribute("sessionCompany");
        List<ReservationResponse.ListDTO> respDTO = reservationService.compReservationList(sessionCompany);
        return respDTO;
    }

    // [숙소 관리] 로그인한 기업이 등록한 숙소 조회
    @GetMapping("/manage/stays")
    public String companyStayList(HttpServletRequest request) {
        SessionCompany sessionCompany = new SessionCompany.SessionCompanyBuilder()
                .id(1)
                .email("com1@nate.com")
                .businessName("김숙박회사")
                .businessNumber("108-32-34324")
                .businessAddress("부산시 해운대구")
                .phone("01011112222")
                .name("김회사")
                .state(ACTIVE)
                .reportCount(0)
                .createdAt(now())
                .build();
        session.setAttribute("sessionCompany", sessionCompany);
//        SessionCompany sessionCompany = (SessionCompany) session.getAttribute("sessionCompany");
        List<CompanyResponse.companyStayListDTO> respDTO = companyService.companyStayList(sessionCompany.getId());
        request.setAttribute("stayList", respDTO);
        return "/company/stay/main";
    }
}

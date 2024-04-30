package com.example.final_project.company;

import com.example.final_project.reservation.ReservationResponse;
import com.example.final_project.reservation.ReservationService;
import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    // 로그인
    @PostMapping("/company/login")
    public String login(CompanyRequest.LoginDTO reqDTO){

        Company company = companyService.login(reqDTO);
        session.setAttribute("sessionUser",company);

        return "redirect:/company/main";
    }

    // 회원가입
    @PostMapping("/company/join")
    public String joinCompany(CompanyRequest.JoinDTO reqDTO) {
        Company company = companyService.joinAndLogin(reqDTO);
        session.setAttribute("sessionUser", company);
        return "redirect:/company/main";
    }

    // 로그인 폼
    @GetMapping("/company")
    public String loginForm(){

        return "/company/login-form";
    }

    // 회원가입 폼
    @GetMapping("/company/join-form")
    public String join(){
        return "/company/join-form";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/company";
    }

    // 숙소관리 메인페이지
    @GetMapping("/company/main")
    public String main(){

        return "/company/stay/main";
    }

}

package com.example.final_project.company;

import com.example.final_project.reservation.ReservationResponse;
import com.example.final_project.reservation.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CompanyController {

    private final CompanyService companyService;
    private final ReservationService reservationService;
    private final HttpSession session;

//    // 예약 현황 확인 (목록)
//    @GetMapping("/reservations/status")
//    public List<ReservationResponse.ListDTO> compReservationList() {
//        SessionCompany sessionCompany = (SessionCompany) session.getAttribute("sessionCompany");
//        List<ReservationResponse.ListDTO> respDTO = reservationService.compReservationList(sessionCompany);
//        return respDTO;
//    }

    // 로그인
    @PostMapping("/company/login")
    public String login(CompanyRequest.LoginDTO reqDTO){

        SessionCompany company= companyService.login(reqDTO);

        session.setAttribute("sessionUser",company);

        return "redirect:/manage/stays";
    }

    // 회원가입
    @PostMapping("/company/join")
    public String joinCompany(CompanyRequest.JoinDTO reqDTO) {
        SessionCompany company = companyService.joinAndLogin(reqDTO);
        session.setAttribute("sessionUser", company);
        return "redirect:/manage/stays";
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

    // [숙소 관리] 로그인한 기업이 등록한 숙소 조회
    @GetMapping("/manage/stays")
    public String companyStayList(HttpServletRequest request) {
        SessionCompany company = (SessionCompany) session.getAttribute("sessionUser");
        List<CompanyResponse.companyStayListDTO> respDTO = companyService.companyStayList(company.getId());
        request.setAttribute("stayList", respDTO);
        return "/company/stay/main";
    }

    // [숙소 관리 - 숙소 상세보기] 로그인한 기업이 등록한 특정 숙소 상세보기
    // [숙소 관리 - 숙소 상세보기 - 객실 상세보기] 로그인한 기업이 등록한 특정 숙소의 객실 상세보기
    @GetMapping("/manage/stays/{stayId}/rooms")

    public String companyRoomList(HttpServletRequest request,
                                  @PathVariable Integer stayId,
                                  @RequestParam(defaultValue = "", name = "tier") String tier) {
        SessionCompany company = (SessionCompany) session.getAttribute("sessionUser");

        List<CompanyResponse.companyStayListDTO> stayRespDTO = companyService.companyStayList(company.getId());
        request.setAttribute("stayList", stayRespDTO);

        if(tier.isBlank()){
            List<CompanyResponse.companyStayDetailDTO> detailRespDTO = companyService.companyStayDetailList(stayId);
            request.setAttribute("detailList", detailRespDTO);

            return "/company/stay/detail";
        }else{
            List<CompanyResponse.companyRoomDetailDTO> respDTO = companyService.companyRoomDetail(stayId, tier);
            request.setAttribute("roomDetailList", respDTO);

            return "/company/room/detail";
        }
    }

    // 정보수정 폼
    @GetMapping("/company/information")
    public String information(){

        return "/company/information/information";
    }

    // 정보수정
    @PostMapping("/information/update/{companyId}")
    public String infoUpdate(@PathVariable Integer companyId,CompanyRequest.UpdateDTO reqDTO){

        SessionCompany company = companyService.updateCompany(companyId,reqDTO);
        session.setAttribute("sessionUser",company);

        return "redirect:/manage/stays";
    }

}

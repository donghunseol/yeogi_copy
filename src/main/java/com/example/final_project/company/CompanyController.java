package com.example.final_project.company;

import com.example.final_project.pay.PayResponse;
import com.example.final_project.reservation.ReservationResponse;
import com.example.final_project.reservation.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CompanyController {

    private final CompanyService companyService;
    private final ReservationService reservationService;
    private final HttpSession session;


    // 로그인
    @PostMapping("/company/login")
    public String login(CompanyRequest.LoginDTO reqDTO) {

        // 현재 시간 및 날짜 가져오기
        LocalDateTime now = LocalDateTime.now();

        // 시간 및 날짜
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedTime = now.format(formatter); // 날짜 및 시간
        String formattedDate = now.format(formatterDate); // 날짜

        SessionCompany company = companyService.login(reqDTO);

        session.setAttribute("sessionUser", company);
        session.setAttribute("loginTime",formattedTime);
        session.setAttribute("today",formattedDate);
        return "redirect:/manage/stays";
    }

    // 회원가입
    @PostMapping("/company/join")
    public String joinCompany(CompanyRequest.JoinDTO reqDTO) {

        companyService.join(reqDTO);

        return "redirect:/company";
    }

    // 로그인 폼
    @GetMapping("/company")
    public String loginForm() {

        return "/company/login-form";
    }

    // 회원가입 폼
    @GetMapping("/company/join-form")
    public String join() {
        return "/company/join-form";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/company";
    }

    // 정보수정 폼
    @GetMapping("/company/information")
    public String information() {

        return "/company/information/information";
    }

    // 정보수정
    @PostMapping("/information/update/{companyId}")
    public String infoUpdate(@PathVariable Integer companyId, CompanyRequest.UpdateDTO reqDTO) {
        SessionCompany company = companyService.updateCompany(companyId, reqDTO);
        session.setAttribute("sessionUser", company);

        return "redirect:/manage/stays";
    }

    // 탈퇴 폼
    @GetMapping("/company/withdraw")
    public String withdraw(){

        return "/company/information/withdraw";
    }

    // 회원탈퇴
    @PostMapping("/company/delete/{companyId}")
    public String infoDelete(@PathVariable Integer companyId, CompanyRequest.DeleteDTO reqDTO){
        SessionCompany company = (SessionCompany) session.getAttribute("sessionUser");
        companyService.deleteCompany(companyId,reqDTO,company);

        return "redirect:/company";
     }

    // [숙소 관리] 로그인한 기업이 등록한 숙소 조회
    @GetMapping("/manage/stays")
    public String companyStayList(HttpServletRequest request) {
        SessionCompany company = (SessionCompany) session.getAttribute("sessionUser");
        List<CompanyResponse.CompanyStayListDTO> respDTO = companyService.companyStayList(company.getId());
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


        if(tier.isBlank()){
            CompanyResponse.CompanyStayListDTO stayRespDTO = companyService.companyStay(stayId);
            request.setAttribute("stayList", stayRespDTO);

            List<CompanyResponse.CompanyStayDetailDTO> detailRespDTO = companyService.companyStayDetailList(stayId);
            request.setAttribute("detailList", detailRespDTO);

            return "/company/stay/detail";
        } else {
            CompanyResponse.CompanyStayListAndTierDTO stayRespDTO = companyService.companyStayListAndTier(stayId, tier);
            request.setAttribute("stayList", stayRespDTO);

            List<CompanyResponse.CompanyRoomDetailDTO> respDTO = companyService.companyRoomDetail(stayId, tier);
            request.setAttribute("roomDetailList", respDTO);

            return "/company/room/detail";
        }
    }

    // [숙소 관리 - 숙소 상세보기 - 객실 상세보기 - 예약 상세보기] 로그인한 기업이 등록한 객실의 예약 상세보기
    @GetMapping("/reservations/{reservationId}/detail")
    public String companyReservationDetail(HttpServletRequest request,
                                           @PathVariable Integer reservationId) {
        CompanyResponse.CompanyReservationDetailDTO respDTO = companyService.companyReservationDetail(reservationId);
        request.setAttribute("reservationDetail", respDTO);

        return "/company/reservation/detail";
    }

    // [숙소 관리 - 수익 조회] 로그인한 기업의 수익 조회
    @GetMapping("/revenue")
    public String companyRevenue(HttpServletRequest request) {
        SessionCompany sessionCompany = (SessionCompany) session.getAttribute("sessionUser");
        PayResponse.TotalIncomeDTO respDTO = companyService.findTotalIncome(sessionCompany);
        List<PayResponse.StayTotalIncomeDTO> listRespDTO = companyService.findIncomeByStay(sessionCompany);
        request.setAttribute("stayCount",listRespDTO.size());
        request.setAttribute("totalIncome", respDTO);
        request.setAttribute("stayTotalIncomeList", listRespDTO);

        return "/company/revenue/main";
    }


    // 예약 현황 확인 (목록)
    @GetMapping("/reservations/status")
    public String compReservationList(HttpServletRequest request, @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        SessionCompany company = (SessionCompany) session.getAttribute("sessionUser");

        if (keyword.isBlank()){
            List<CompanyResponse.ReservationListDTO> respDTO = reservationService.compReservationList(company);
            request.setAttribute("reservationCount", respDTO.size());
            request.setAttribute("reservationList", respDTO);
        }else {
            List<CompanyResponse.ReservationListDTO> respDTO = reservationService.reservationByKeyword(company,keyword);
            request.setAttribute("reservationCount", respDTO.size());
            request.setAttribute("keywordReservationList", respDTO);
        }

        return "/company/reservation/main";
    }

    // 예약 현황 확인 (상세보기)
    @GetMapping("/reservations/{reservationId}")
    public String compReservationDetail(@PathVariable Integer reservationId, HttpServletRequest request) {
        CompanyResponse.ReservationDetailDTO respDTO = reservationService.comReservationDetail(reservationId);
        request.setAttribute("reservationDetail", respDTO);
        return "/company/reservation/main-detail";
    }
}

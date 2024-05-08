package com.example.final_project.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminService adminService;
    private final HttpSession session;

    @GetMapping("/admin")
    public String login() {
        return "/admin/login";
    }

    @GetMapping("/admin/join")
    public String join() {
        return "/admin/customer-c/report";
    }

    // 기업 회원 정보 조회 View
    @GetMapping("/admin/company")
    public String company(HttpServletRequest request) {
        List<AdminResponse.companyListDTO> respDTO = adminService.adminCompanyList();
        request.setAttribute("companyList", respDTO);
        request.setAttribute("companyCount", respDTO.size());
        return "/admin/customer-c/join";
    }

    // 개인 회원 정보 조회 View
    @GetMapping("/admin/users")
    public String user(HttpServletRequest request) {
        List<AdminResponse.userListDTO> respDTO = adminService.adminUserList();
        request.setAttribute("userCount", respDTO.size());
        request.setAttribute("userList", respDTO);
        return "/admin/customer-u/join";
    }

    // 기업 블랙 등록 기능
    @PutMapping("/admin/company/black/{companyId}")
    public String companyBlack(@PathVariable Integer companyId) {
        adminService.adminCompanyBlack(companyId);
        return "redirect:/admin/company";
    }

    // 기업 블랙 취소 기능
    @PutMapping("/admin/company/black/cancel/{companyId}")
    public String companyBlackCancel(@PathVariable Integer companyId) {
        adminService.adminCompanyBlackCancel(companyId);
        return "redirect:/admin/company";
    }

    // 관리자 페이지에서 특정 회원의 예약 내역 리스트
    @GetMapping("/admin/users/{userId}/reservations")
    public String userReservationList(@PathVariable Integer userId, HttpServletRequest request) {
        List<AdminResponse.userReservationDTO> respDTO = adminService.adminReservationList(userId);
        System.out.println(respDTO);
        request.setAttribute("reservationCount", respDTO.size());
        request.setAttribute("reservationList", respDTO);
        return "/admin/customer-u/user-reservations";
    }

    // 관리자 페이지에서 특정 회원의 예약 상세보기 뷰
    @GetMapping("/admin/users/{userId}/reservations/{reservationId}")
    public String userReservationDetailList(@PathVariable Integer userId,
                                            @PathVariable Integer reservationId,
                                            HttpServletRequest request) {
        AdminResponse.userReservationDetailDTO respDTO = adminService.adminReservationDetailList(reservationId);
        request.setAttribute("reservationDetail", respDTO);
        return "";
    }

//    // 관리자 페이지에서 특정 개인이 작성한 리뷰 리스트
//    @GetMapping("/admin/users/{userId}/reviews")
//    public String userReviewList (@PathVariable Integer userId, HttpServletRequest request){
//        List<AdminResponse.userReviewListDTO> respDTO = adminService.userReviewList(userId);
//        request.setAttribute("reviewCount", respDTO.size());
//        request.setAttribute("reviewList", respDTO);
//        return "/admin/customer-u/review/main";
//    }

//    // 관리자 페이지에서 특정 기업의 숙소 정보 출력
//    @GetMapping("/admin/companies/{companyId}/stays}")
//    public String companyStayList (@PathVariable Integer companyId, HttpServletRequest request){
//
//    }
}

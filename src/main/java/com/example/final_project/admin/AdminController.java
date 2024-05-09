package com.example.final_project.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 개인 회원 정보 조회 View
    @GetMapping("/admin/users")
    public String user(HttpServletRequest request) {
        List<AdminResponse.UserListDTO> respDTO = adminService.adminUserList();
        request.setAttribute("userCount", respDTO.size());
        request.setAttribute("userList", respDTO);
        return "/admin/customer-u/join";
    }

    // 특정 회원의 정보 상세보기
    @GetMapping("/admin/users/{userId}")
    public String userDetail(@PathVariable Integer userId, HttpServletRequest request) {
        AdminResponse.UserDetailDTO respDTO = adminService.adminUserDetail(userId);
        request.setAttribute("userDetail", respDTO);
        return "/admin/customer-u/join-detail";
    }

    // 블랙 리스트에 추가 (개인)
    @PutMapping("/admin/users/{userId}/black-list/add")
    @ResponseBody // JSON 또는 다른 응답 본문을 반환하기 위해 필요
    public Map<String, String> addUserToBlackList(@PathVariable Integer userId) {
        adminService.addUserToBlackList(userId);
        // 리다이렉션 URL을 포함하는 JSON 객체 반환
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/admin/users");
        return response;
    }

    // 블랙 리스트에서 삭제 (개인)
    @PutMapping("/admin/users/{userId}/black-list/remove")
    @ResponseBody // JSON 또는 다른 응답 본문을 반환하기 위해 필요
    public Map<String, String> removeUserFromBlackList(@PathVariable Integer userId) {
        adminService.removeUserFromBlackList(userId);
        // 리다이렉션 URL을 포함하는 JSON 객체 반환
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/admin/users");
        return response;
    }

    // 기업 회원 정보 조회 View
    @GetMapping("/admin/companies")
    public String company(HttpServletRequest request) {
        List<AdminResponse.CompanyListDTO> respDTO = adminService.adminCompanyList();
        request.setAttribute("companyList", respDTO);
        request.setAttribute("companyCount", respDTO.size());
        return "/admin/customer-c/join";
    }

    // 특정 기업의 정보 상세보기
    @GetMapping("/admin/companies/{companyId}")
    public String companyDetail(@PathVariable Integer companyId, HttpServletRequest request) {
        AdminResponse.CompanyDetailDTO respDTO = adminService.adminCompanyDetail(companyId);
        request.setAttribute("companyDetail", respDTO);
        return "/admin/customer-c/join-detail";
    }

    // 블랙 리스트에 추가 (기업)
    @PutMapping("/admin/companies/{companyId}/black-list/add")
    @ResponseBody // JSON 또는 다른 응답 본문을 반환하기 위해 필요
    public Map<String, String> addCompanyToBlackList(@PathVariable Integer companyId) {
        adminService.addCompanyToBlackList(companyId);
        // 리다이렉션 URL을 포함하는 JSON 객체 반환
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/admin/companies");
        return response;
    }

    // 블랙 리스트에서 삭제 (기업)
    @PutMapping("/admin/companies/{companyId}/black-list/remove")
    @ResponseBody // JSON 또는 다른 응답 본문을 반환하기 위해 필요
    public Map<String, String> removeCompanyFromBlackList(@PathVariable Integer companyId) {
        adminService.removeCompanyFromBlackList(companyId);
        // 리다이렉션 URL을 포함하는 JSON 객체 반환
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/admin/companies");
        return response;
    }

    // 관리자 페이지에서 특정 회원의 예약 내역 리스트
    @GetMapping("/admin/users/{userId}/reservations")
    public String userReservationList(@PathVariable Integer userId, HttpServletRequest request) {
        List<AdminResponse.UserReservationDTO> respDTO = adminService.adminReservationList(userId);
        System.out.println(respDTO);
        request.setAttribute("reservationCount", respDTO.size());
        request.setAttribute("reservationList", respDTO);
        return "/admin/customer-u/user-reservations";
    }

//    // 관리자 페이지에서 특정 회원의 예약 상세보기 뷰
//    @GetMapping("/admin/users/{userId}/reservations/{reservationId}")
//    public String userReservationDetailList(@PathVariable Integer userId,
//                                            @PathVariable Integer reservationId,
//                                            HttpServletRequest request) {
//        AdminResponse.UserReservationDetailDTO respDTO = adminService.adminReservationDetailList(reservationId);
//        request.setAttribute("reservationDetail", respDTO);
//        return "";
//    }

//    // 관리자 페이지에서 특정 개인이 작성한 리뷰 리스트
//    @GetMapping("/admin/users/{userId}/reviews")
//    public String userReviewList (@PathVariable Integer userId, HttpServletRequest request){
//        List<AdminResponse.userReviewListDTO> respDTO = adminService.userReviewList(userId);
//        request.setAttribute("reviewCount", respDTO.size());
//        request.setAttribute("reviewList", respDTO);
//        return "/admin/customer-u/review/main";
//    }

    // 관리자 페이지에서 특정 기업의 숙소 정보 출력
    @GetMapping("/admin/companies/{companyId}/stays")
    public String companyStayList (@PathVariable Integer companyId, HttpServletRequest request){
        List<AdminResponse.CompanyStayListDTO> respDTO = adminService.adminCompanyStayList(companyId);
        request.setAttribute("stayCount", respDTO.size());
        request.setAttribute("stayList", respDTO);
        return "/admin/customer-c/stay-list";
    }
}

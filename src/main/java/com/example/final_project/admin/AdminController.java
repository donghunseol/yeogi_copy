package com.example.final_project.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminService adminService;
    private final HttpSession session;

    @GetMapping("/admin")
    public String loginForm() {
        return "/admin/login";
    }

    @PostMapping("/admin/login")
    public String login(AdminRequest.LoginDTO reqDTO){

        // 현재 시간 및 날짜 가져오기
        LocalDateTime now = LocalDateTime.now();

        // 시간 및 날짜
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedTime = now.format(formatter); // 날짜 및 시간
        String formattedDate = now.format(formatterDate); // 날짜

        SessionAdmin admin = adminService.login(reqDTO);

        session.setAttribute("sessionUser", admin);
        session.setAttribute("loginTime",formattedTime);
        session.setAttribute("today",formattedDate);


        return "redirect:/admin/companies";
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
        Admin admin = (Admin) request.getAttribute("sessionUser");
        System.out.println(admin);

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


    // 기업 가입 승인
    @PutMapping("/admin/companies/{companyId}/active")
    @ResponseBody // JSON 또는 다른 응답 본문을 반환하기 위해 필요
    public Map<String, String> activeCompany(@PathVariable Integer companyId) {
        adminService.activeCompany(companyId);
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/admin/companies");
        return response;
    }

    // 기업 가입 거절
    @PutMapping("/admin/companies/{companyId}/reject")
    @ResponseBody // JSON 또는 다른 응답 본문을 반환하기 위해 필요
    public Map<String, String> rejectCompany(@PathVariable Integer companyId) {
        adminService.rejectCompany(companyId);
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/admin/companies");
        return response;
    }
    // 기업 문의 사항리스트
    @GetMapping("/admin/company/question")
    public String questionList(HttpServletRequest request){
          SessionAdmin sessionUser = (SessionAdmin) session.getAttribute("sessionUser");
          List<AdminResponse.CompanyQuestionListDTO> respDTO = adminService.adminCompanyQuestionList(sessionUser);

          request.setAttribute("questionList",respDTO);
          return "/admin/customer-c/question";
    }
}

package com.example.final_project.admin;

import com.example.final_project.company.Company;
import com.example.final_project.question.Question;
import com.example.final_project.review.Review;
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
import java.util.stream.Collectors;

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

    @GetMapping("/admin/logout")
    public String logout(){
        session.invalidate();
        return "/admin";
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
        System.out.println(respDTO);
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
    public String company(HttpServletRequest request, @RequestParam(value = "keyword", defaultValue = "") String keyword) {

        if (keyword.isBlank()) {
            List<AdminResponse.CompanyListDTO> respDTO = adminService.adminCompanyList();
            request.setAttribute("companyList", respDTO);
            request.setAttribute("companyCount", respDTO.size());
        } else {
            List<AdminResponse.CompanyKeywordList> companyList = adminService.serarchKeyword(keyword);
            request.setAttribute("keywordList",companyList);
            request.setAttribute("keywordCount",companyList.size());
        }

        request.setAttribute("keyword", keyword);

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

    // 기업 문의사항 리스트
    @GetMapping("/admin/company/question")
    public String companyQuestionList(HttpServletRequest request){
       SessionAdmin sessionUser = (SessionAdmin) session.getAttribute("sessionUser");
       List<AdminResponse.CompanyQuestionListDTO> respDTO = adminService.adminCompanyQuestionList(sessionUser);
       Integer listSize = respDTO.size();
       request.setAttribute("listCount",listSize);
       request.setAttribute("questionList",respDTO);
       return "/admin/customer-c/question";
    }

    // 기업 문의사항 디테일
    @GetMapping("/admin/company-question/detail/{questionId}")
    public String companyQuestionDetail(@PathVariable Integer questionId, HttpServletRequest request){
        SessionAdmin sessionUser = (SessionAdmin) session.getAttribute("sessionUser");
        AdminResponse.CompanyQuestionDetailDTO respDTO = adminService.adminCompanyQuestionDetail(questionId,sessionUser);
        request.setAttribute("questionDetail",respDTO);
        return "/admin/customer-c/question-detail";
    }

    // 관리자 문의사항 답글작성
    @PostMapping("/admin/answer/company")
    public String adminQuestionAnswer(AdminRequest.AdminAnswerDTO reqDTO){
        System.out.println(reqDTO);
        SessionAdmin sessionUser = (SessionAdmin) session.getAttribute("sessionUser");
        adminService.adminQuestionAnswer(sessionUser,reqDTO);

        return "redirect:/admin/company/question";

//      String redirectPage = ""; // 변수 초기화
//        if (reqDTO.getCompanyId() != null){
//            // 유저 답글을 다는 경우
//            redirectPage = "redirect:/admin/company/question"; // 오타 수정: "redierect" -> "redirect"
//        } else {
//            // 기타 경우에 대한 처리
//        }
//
//        return redirectPage; // 오타 수정:
    }

    // 신고 목록
    @GetMapping("/admin/reports")
    public String reportList(HttpServletRequest request){
        List<AdminResponse.ReportList> respDTOS = adminService.reportedReviewList();
        System.out.println(respDTOS);
        request.setAttribute("reportCount", respDTOS.size());
        request.setAttribute("reportList", respDTOS);
        return "/admin/review/report";
    }

    // 신고 상세보기
    @GetMapping("/admin/reports/{reportId}")
    public String reportDetail(HttpServletRequest request, @PathVariable Integer reportId){
        AdminResponse.ReportDetail respDTO = adminService.reportedReviewDetail(reportId);
        request.setAttribute("reportDetail", respDTO);
        return "/admin/review/detail";
    }

    //관리자 FAQ 리스트
    @GetMapping("/admin/faq")
    public String adminFaqList(HttpServletRequest request){
        SessionAdmin sessionUser = (SessionAdmin) session.getAttribute("sessionUser");
        List<AdminResponse.adminFaqListDTO> respDTO = adminService.adminFaqList(sessionUser);

        Integer listCount = respDTO.size();

        request.setAttribute("count",listCount);
        request.setAttribute("faqList",respDTO);

        return "/admin/customer-c/faq";
    }

    //관리자 FAQ 디테일
    @GetMapping("/admin/faq/{faqId}")
    public String adminFaqDetail(@PathVariable Integer faqId, HttpServletRequest request){
        SessionAdmin sessionUser = (SessionAdmin) session.getAttribute("sessionUser");
        AdminResponse.adminFaqDetail respDTO = adminService.adminFaqDetail(faqId, sessionUser);

        request.setAttribute("faqDetail",respDTO);

        return "/admin/customer-c/faq-detail";
    }

    //관리자 FAQ 작성폼
    @GetMapping("/admin/faq/write-form")
    public String adminFaqWriteForm(){

        return "/admin/customer-c/faq-write";
    }

    //관리자 FAQ 작성
    @PostMapping("/admin/write")
    public String adminWrite(AdminRequest.AdminFaqDTO reqDTO){
        SessionAdmin sessionUser = (SessionAdmin) session.getAttribute("sessionUser");
        adminService.faqRegister(sessionUser,reqDTO);

        return "redirect:/admin/faq";
    }

}

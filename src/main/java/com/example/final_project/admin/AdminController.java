package com.example.final_project.admin;

import com.example.final_project.company.SessionCompany;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import static com.example.final_project._core.enums.CompanyEnum.BLACK;

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
    public String company(HttpServletRequest request, Integer companyId) {
//        SessionCompany sessionCompany = adminService.adminCompanyBlack(companyId);

        // 블랙 리스트 정보 저장
        Boolean isBlack = false; // true 면 블랙 등록 / false 면 블랙 미등록
        Boolean isBlackCancel = true; // true 면 블랙 취소 버튼 활성 / false 면 블랙 취소 버튼 비활성

        // 블랙 리스트 인지 아닌지 확인
        // 블랙이 아닌 경우 -> 회원 가입 진행 중 또는 회원 가입 완료 (거절은 제외)
        // 블랙인 경우 제외
        // 블랙 취소 버튼 -> BLACK (true)
        // 블랙 버튼을 활성화 해야 하는 경우 -> ACTIVE 만 (false)
        // 블랙 버튼 비활성화 -> PROGRESSING, QUIT, REJECT (false)
//        if (sessionCompany.getState().equals(BLACK)) {
//            isBlack = true;
//            isBlackCancel = false;
//        } else {
//            isBlack = false;
//            isBlackCancel = true;
//        }

        request.setAttribute("isBlack", isBlack);
        request.setAttribute("isBlackCancel", isBlackCancel);

        return "/admin/customer-c/join";
    }

    // 기업 블랙 취소 기능
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

    //
    @GetMapping("/admin/user")
    public String user(){

        return "/admin/customer-u/join";
    }
}

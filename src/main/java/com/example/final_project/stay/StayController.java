package com.example.final_project.stay;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.company.Company;
import com.example.final_project.company.SessionCompany;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class StayController {
    private final StayService stayService;
    private final HttpSession session;


    //숙소 등록폼
    @GetMapping("/stay/register-form")
    public String stayRegisterForm(){

        return "/company/stay/register";
    }

    //숙소 등록
    @PostMapping("/stay/register")
    public String stayRegister(StayRequest.SaveDTO reqDTO){
        SessionCompany sessionUser = (SessionCompany) session.getAttribute("sessionUser");
        stayService.register(reqDTO,sessionUser);

        return "redirect:/manage/stays";
    }
}

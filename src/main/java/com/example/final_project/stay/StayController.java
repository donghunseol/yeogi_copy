package com.example.final_project.stay;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.company.Company;
import com.example.final_project.company.SessionCompany;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class StayController {
    private final StayService stayService;
    private final HttpSession session;


    //숙소 등록폼
    @GetMapping("/stay/register-form")
    public String RegisterForm(){

        return "/company/stay/register";
    }

    //숙소 등록
    @PostMapping("/stay/register")
    public String Register(StayRequest.SaveDTO reqDTO){

        SessionCompany sessionUser = (SessionCompany) session.getAttribute("sessionUser");
        stayService.register(reqDTO, sessionUser);

        return "redirect:/manage/stays";
    }

    //숙소 업데이트 폼
    @GetMapping("/stay/update-form/{stayId}")
    public String UpdateForm(@PathVariable Integer stayId, HttpServletRequest request){
        SessionCompany sessionUser = (SessionCompany) session.getAttribute("sessionUser");

        StayResponse.UpdateFormDTO respDTO =  stayService.updateForm(stayId,sessionUser);
        request.setAttribute("stayInfo",respDTO);

        return "/company/stay/update-form";
    }

    //숙소 업데이트
    @PostMapping("/stay/update/{stayId}")
    public String Update(@PathVariable Integer stayId, StayRequest.UpdateDTO reqDTO){
        SessionCompany sessionUser = (SessionCompany) session.getAttribute("sessionUser");
        stayService.update(stayId, sessionUser, reqDTO);

        return "redirect:/manage/stays";
    }

    //숙소 삭제
    @PostMapping("/stay/delete/{stayId}")
    public String Delete(@PathVariable Integer stayId){
        SessionCompany sessionUser = (SessionCompany) session.getAttribute("sessionUser");
        stayService.delete(stayId, sessionUser);

        return "redirect:/manage/stays";
    }
}

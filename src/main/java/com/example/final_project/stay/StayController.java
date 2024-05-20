package com.example.final_project.stay;

import com.example.final_project.company.SessionCompany;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
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
    public String Register(HttpServletRequest request,
                           @Valid @ModelAttribute StayRequest.SaveDTO reqDTO, Errors errors)
    {
        System.out.println(reqDTO);

        // 파일 리스트 초기화
        List<MultipartFile> imgFiles = new ArrayList<>();

        // HttpServletRequest에서 파일을 읽어와서 리스트에 추가
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("imgFiles")) {
                MultipartFile file = ((MultipartHttpServletRequest) request).getFile(paramName);
                imgFiles.add(file);
            }
        }

        SessionCompany sessionUser = (SessionCompany) session.getAttribute("sessionUser");
        stayService.register(reqDTO,sessionUser);

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
    public String Update(@PathVariable Integer stayId, @Valid StayRequest.UpdateDTO reqDTO, Errors errors){
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

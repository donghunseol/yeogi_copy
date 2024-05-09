package com.example.final_project.room;

import com.example.final_project.company.SessionCompany;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class RoomController {
    private final RoomService roomsService;
    private final HttpSession session;


    //객실 등록 폼
    @GetMapping("/room/register-form/{stayId}")
    public String registerForm(@PathVariable Integer stayId, HttpServletRequest request){

        request.setAttribute("stay",stayId);

        return "/company/room/register";
    }

    //객실 등록
    @PostMapping("/room/register/{stayId}")
    public String register(@PathVariable Integer stayId, @ModelAttribute RoomRequest.SaveDTO reqDTO){
        SessionCompany sessionUser = (SessionCompany) session.getAttribute("sessionUser");
        System.out.println(reqDTO);
        roomsService.register(stayId,sessionUser,reqDTO);

        return "redirect:/manage/stays/"+stayId+"/rooms";
    }
}

package com.example.final_project.admin;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminService adminService;
    private final HttpSession session;

    @GetMapping("/admin/login")
    public String login(){

        return "/admin/login";
    }

    @GetMapping("/customer-c/join")
    public String join(){
        return "/admin/customer-c/join";
    }

}

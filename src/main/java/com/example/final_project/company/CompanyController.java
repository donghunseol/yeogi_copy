package com.example.final_project.company;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class CompanyController {
    private final CompanyService companyService;
    private final HttpSession session;
}

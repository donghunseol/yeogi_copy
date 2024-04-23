package com.example.final_project.faq;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class FaqController {
    private final FaqService faqService;
    private final HttpSession session;
}

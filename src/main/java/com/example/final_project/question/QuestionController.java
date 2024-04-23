package com.example.final_project.question;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;
    private final HttpSession session;
}

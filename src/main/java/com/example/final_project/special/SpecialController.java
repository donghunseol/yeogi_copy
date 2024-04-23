package com.example.final_project.special;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class SpecialController {
    private final SpecialService specialService;
    private final HttpSession session;
}

package com.example.final_project.option;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class OptionController {
    private final OptionService optionService;
    private final HttpSession session;
}

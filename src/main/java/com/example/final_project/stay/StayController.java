package com.example.final_project.stay;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class StayController {
    private final StayService stayService;
    private final HttpSession session;
}

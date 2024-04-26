package com.example.final_project.stay_image;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class StayImageController {
    private final StayImageService scrapService;
    private final HttpSession session;
}

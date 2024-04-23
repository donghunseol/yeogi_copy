package com.example.final_project.review;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ReviewController {
    private final ReviewService reviewService;
    private final HttpSession session;
}

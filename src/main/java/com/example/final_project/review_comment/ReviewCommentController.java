package com.example.final_project.review_comment;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ReviewCommentController {
    private final ReviewCommentService reviewCommentService;
    private final HttpSession session;
}

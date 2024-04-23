package com.example.final_project.pay;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class PayController {
    private final PayService payService;
    private final HttpSession session;
}

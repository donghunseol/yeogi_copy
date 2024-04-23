package com.example.final_project.event;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class EventController {
    private final EventService eventService;
    private final HttpSession session;
}

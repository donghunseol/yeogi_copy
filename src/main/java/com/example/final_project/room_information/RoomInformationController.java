package com.example.final_project.room_information;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class RoomInformationController {
    private final RoomInformationService roomInformationService;
    private final HttpSession session;
}

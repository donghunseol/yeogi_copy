package com.example.final_project.room;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class RoomController {
    private final RoomService roomsService;
    private final HttpSession session;
}

package com.example.final_project.user;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.reservation.ReservationResponse;
import com.example.final_project.reservation.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final ReservationService reservationService;
    private final HttpSession session;

    
    // 회원 로그인
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        SessionUser sessionUser = userService.login(reqDTO);
        session.setAttribute("sessionUser", sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 회원 가입 후 로그인
    @PostMapping("/users/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        SessionUser sessionUser = userService.joinAndLogin(reqDTO);
        session.setAttribute("sessionUser", sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 회원 정보 수정
    @PutMapping("/users/{userId}")
    public ResponseEntity<?> update(@PathVariable Integer userId, @RequestBody UserRequest.UpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser newSessionUser = userService.update(sessionUser, reqDTO, userId);
        session.setAttribute("sessionUser", newSessionUser);

        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
    }

    // 예약 내역 페이지 - 목록
    @GetMapping("/my-reservations")
    public List<ReservationResponse.ListDTO> reservationList() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ReservationResponse.ListDTO> respDTO = reservationService.reservationList(sessionUser);
        return respDTO;
    }

}

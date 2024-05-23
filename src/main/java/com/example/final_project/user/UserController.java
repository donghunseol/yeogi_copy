package com.example.final_project.user;

import com.example.final_project._core.errors.exception.Exception400;
import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.reservation.ReservationResponse;
import com.example.final_project.reservation.ReservationService;
import com.example.final_project.stay.StayResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final ReservationService reservationService;
    private final HttpSession session;

    // 회원 로그인
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserRequest.LoginDTO reqDTO, Errors errors) {
        String jwt = userService.login(reqDTO);
        UserResponse.LoginDTO respDTO = userService.loginByDTO(reqDTO);
        return ResponseEntity.ok().header("Authorization", "Bearer " + jwt).body(new ApiUtil<>(respDTO));
    }

    // 회원 가입
    @PostMapping("/users/join")
    public ResponseEntity<?> join(@Valid @RequestBody UserRequest.JoinDTO reqDTO, Errors errors) {

        String jwt = userService.joinAndLogin(reqDTO);
        UserResponse.JoinDTO respDTO = userService.joinByDTO(reqDTO);
        return ResponseEntity.ok().header("Authorization", "Bearer " + jwt).body(new ApiUtil<>(respDTO));
    }


    //회원가입 시 이메일중복 체크확인
    @GetMapping("/users/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(String email){
        Optional userOp = userService.findByEmail(email);
        if (userOp == null) {
            return new ApiUtil<>(true);
        } else {
            return new ApiUtil<>(false);
        }
    }

    // 회원 정보 수정
    @PutMapping("/api/users/{userId}")
    public ResponseEntity<?> update(@PathVariable Integer userId, @Valid @RequestBody UserRequest.UpdateDTO reqDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser newSessionUser = userService.update(sessionUser, reqDTO, userId);
        session.setAttribute("sessionUser", newSessionUser);

        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
    }

    // 로그인한 회원의 예약 내역 페이지
    @GetMapping("/api/my-reservations")
    public ResponseEntity<?> userReservationList() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ReservationResponse.ReservationDTO> respDTO = reservationService.userReservationList(sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

//    // 로그인한 회원의 예약 내역 페이지 - 목록
//    @GetMapping("/api/my-reservations")
//    public ResponseEntity<?> userReservationList() {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
//        List<ReservationResponse.ListDTO> respDTO = reservationService.userReservationList(sessionUser);
//        return ResponseEntity.ok(new ApiUtil<>(respDTO));
//    }
//
    // 로그인한 회원의 예약 내역 페이지 - 상세보기
    @GetMapping("/api/my-reservations/{reservationId}")
    public ResponseEntity<?> reservationDetail(@PathVariable Integer reservationId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ReservationResponse.DetailDTO respDTO = reservationService.reservationDetail(sessionUser, reservationId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 로그인 한 회원의 알림 목록
    @GetMapping("/api/my-notifications")
    public ResponseEntity<?> myNotifications(){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<UserResponse.Notifications> respDTOS = userService.notifications(sessionUser);
        return ResponseEntity.ok().body(new ApiUtil<>(respDTOS));
    }

    // FAQ리스트
    @GetMapping("/users/faq")
    public ResponseEntity<?> faqList(){

        List<UserResponse.FaqListDTO> respDTO = userService.faqList();
        return ResponseEntity.ok().body(new ApiUtil<>(respDTO));
    }

    // 객실 별 예약 조회 (달력)
    @GetMapping("/room/detail/{roomId}/calendar")
    public ResponseEntity<?> ReservationForCalendar(@PathVariable Integer roomId){
        List<UserResponse.ReservationForCalendarDTO> respDTO = userService.reservationForCalendar(roomId);
        return ResponseEntity.ok().body(new ApiUtil<>(respDTO));
    }

    // [유저] 문의사항 작성
    @PostMapping("/api/users/question")
    public ResponseEntity<?> questionWrite(@Valid @RequestBody UserRequest.QuestionSave reqDTO, Errors errors){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        userService.questionWrite(sessionUser,reqDTO);

        return ResponseEntity.ok().body(new ApiUtil<>("문의작성 성공"));
    }
}

package com.example.final_project.controller;

import com.example.final_project.MyWithRestDoc;
import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.xml.validation.Validator;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest extends MyWithRestDoc {

    private static ObjectMapper om;
    public static String jwt;

    @BeforeAll
    public static void setup() {
        om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        jwt = JwtUtil.userCreate(
                User.builder()
                        .id(1)
                        .email("ssar@nate.com")
                        .build());
    }

    @Test
    public void login_success_test() throws Exception {
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setEmail("ssar@nate.com");
        reqDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/users/login")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : "+respBody);
        String jwt = actions.andReturn().getResponse().getHeader("Authorization");
        System.out.println("jwt = " + jwt);

        // then
        actions.andExpect(status().isOk()); // header 검증
        actions.andExpect(result -> result.getResponse().getHeader("Authorization").contains("Bearer " + jwt));
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.email").value("ssar@nate.com"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void login_fail_test() throws Exception {
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setEmail("ssar@nate.com");
        reqDTO.setPassword("12345");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/users/login")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : "+respBody);
        String jwt = actions.andReturn().getResponse().getHeader("Authorization");
        System.out.println("jwt = " + jwt);

        // then
        actions.andExpect(status().isUnauthorized()); // header 검증
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.errorMessage").value("인증되지 않았습니다"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void join_success_test() throws Exception {
        // given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setEmail("ccar@nate.com");
        reqDTO.setPassword("13579");
        reqDTO.setName("code");
        reqDTO.setPhone("010-1133-5577");
        reqDTO.setBirth(LocalDate.of(2020, 2, 2));

        String reqBody = om.writeValueAsString(reqDTO);
//        System.out.println("reqBody : " + reqBody);

        Errors errors = new BeanPropertyBindingResult(reqDTO, "reqDTO");
        if (errors.hasErrors()) {
            throw new IllegalArgumentException("유효하지 않은 요청입니다: " + errors.getAllErrors());
        }


        // when
        ResultActions actions = mvc.perform(
                post("/users/join")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.id").value(4));
        actions.andExpect(jsonPath("$.body.email").value("ccar@nate.com"));
        actions.andExpect(jsonPath("$.body.name").value("code"));
        actions.andExpect(jsonPath("$.body.phone").value("010-1133-5577"));
        actions.andExpect(jsonPath("$.body.state").value("ACTIVE"));
        actions.andExpect(jsonPath("$.body.birth").value("2020-02-02"));
        actions.andExpect(jsonPath("$.body.reportCount").value(0));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 회원가입 시 이메일중복 체크확인
    @Test
    public void username_same_check_success_test() throws Exception {
        // given
        String email = "ssar@nate.com";

        String reqBody = om.writeValueAsString(email);

        // when
        ResultActions actions = mvc.perform(
                get("/users/username-same-check")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body").value(false));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

//    // 회원 정보 수정
//    @Test
//    public void update_success_test() throws Exception {
//        // given
//        Integer userId = 1;
//        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
//        reqDTO.setName("coding");
//        reqDTO.setPhone("010-2244-6688");
//
//        String reqBody = om.writeValueAsString(reqDTO);
//
//        // when
//        ResultActions actions = mvc.perform(
//                put("/api/users/"+userId)
//                        .header("Authorization", "Bearer " + jwt)
//                        .content(reqBody)
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        // eye
//        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody : " + respBody);
//
//        // then
//        actions.andExpect(jsonPath("$.status").value(200));
//        actions.andExpect(jsonPath("$.msg").value("성공"));
//        actions.andExpect(jsonPath("$.body.id").value(1));
//        actions.andExpect(jsonPath("$.body.name").value("coding"));
//        actions.andExpect(jsonPath("$.body.phone").value("010-2244-6688"));
//        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
//    }


    // 로그인한 회원의 예약 내역 페이지
    @Test
    public void user_reservation_list_test() throws Exception {
        // given
        Integer userId = 1;

        // when
        ResultActions actions = mvc.perform(
                get("/api/my-reservations")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].reservationId").value(1));
        actions.andExpect(jsonPath("$.body[0].userId").value(1));
        actions.andExpect(jsonPath("$.body[0].stayName").value("호텔 블루 하버"));
        actions.andExpect(jsonPath("$.body[0].stayAddress").value("부산시 해운대구 좌동순환로 99 (좌동)"));
        actions.andExpect(jsonPath("$.body[0].checkInDate").value("2023-12-31"));
        actions.andExpect(jsonPath("$.body[0].checkInTime").value("15:00:00"));
        actions.andExpect(jsonPath("$.body[0].checkOutDate").value("2024-01-01"));
        actions.andExpect(jsonPath("$.body[0].checkOutTime").value("11:00:00"));
        actions.andExpect(jsonPath("$.body[0].roomId").value(1));
        actions.andExpect(jsonPath("$.body[0].roomName").value("스위트룸"));
        actions.andExpect(jsonPath("$.body[0].roomImageName").value("room1.jpg"));
        actions.andExpect(jsonPath("$.body[0].roomImagePath").value("/images/room1.jpg"));
        actions.andExpect(jsonPath("$.body[0].reservationName").value("홍길동"));
        actions.andExpect(jsonPath("$.body[0].reservationTel").value("01012344321"));
        actions.andExpect(jsonPath("$.body[0].reviewId").isEmpty());
        actions.andExpect(jsonPath("$.body[0].payId").value(1));
        actions.andExpect(jsonPath("$.body[0].payState").value("결제 완료"));
        actions.andExpect(jsonPath("$.body[0].payAt").value("2023-12-29T13:00:00"));
        actions.andExpect(jsonPath("$.body[0].amount").value(150000));
        actions.andExpect(jsonPath("$.body[0].way").value("카드 결제"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }


    // 로그인한 회원의 예약 내역 페이지 - 상세보기
    @Test
    public void reservation_detail_success_test() throws Exception {
        // given
        Integer reservationId = 1;

        // when
        ResultActions actions = mvc.perform(
                get("/api/my-reservations/"+reservationId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.reservationId").value(1));
        actions.andExpect(jsonPath("$.body.userId").value(1));
        actions.andExpect(jsonPath("$.body.stayName").value("호텔 블루 하버"));
        actions.andExpect(jsonPath("$.body.stayAddress").value("부산시 해운대구 좌동순환로 99 (좌동)"));
        actions.andExpect(jsonPath("$.body.checkInDate").value("2023-12-31"));
        actions.andExpect(jsonPath("$.body.checkInTime").value("15:00:00"));
        actions.andExpect(jsonPath("$.body.checkOutDate").value("2024-01-01"));
        actions.andExpect(jsonPath("$.body.checkOutTime").value("11:00:00"));
        actions.andExpect(jsonPath("$.body.roomId").value(1));
        actions.andExpect(jsonPath("$.body.roomName").value("스위트룸"));
        actions.andExpect(jsonPath("$.body.reservationName").value("홍길동"));
        actions.andExpect(jsonPath("$.body.reservationTel").value("01012344321"));
        actions.andExpect(jsonPath("$.body.reviewId").isEmpty());
        actions.andExpect(jsonPath("$.body.payAt").value("2023-12-29T13:00:00"));
        actions.andExpect(jsonPath("$.body.amount").value(150000));
        actions.andExpect(jsonPath("$.body.way").value("Credit Card"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }



    // 로그인 한 회원의 알림 목록
    @Test
    public void my_notifications_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/api/my-notifications")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].reservationId").value(1));
        actions.andExpect(jsonPath("$.body[0].checkInDate").value("2023-12-31"));
        actions.andExpect(jsonPath("$.body[0].checkOutDate").value("2024-01-01"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }



    // FAQ리스트
    @Test
    public void faq_list_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/users/faq")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].faqId").value(1));
        actions.andExpect(jsonPath("$.body[0].classification").value("User"));
        actions.andExpect(jsonPath("$.body[0].content").value("예약을 취소하고 싶어요"));
        actions.andExpect(jsonPath("$.body[0].reply").value("예약취소는  앱/웹 > 내정보 > 예약/구매내역에서 직접 가능합니다."));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }


    // 객실 별 예약 조회 (달력)
    @Test
    public void reservation_for_calendar_success_test() throws Exception {
        // given
        Integer roomId = 1;

        // when
        ResultActions actions = mvc.perform(
                get("/room/detail/" + roomId + "/calendar")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].reservationId").value(1));
        actions.andExpect(jsonPath("$.body[0].checkInDate").value("2023-12-31"));
        actions.andExpect(jsonPath("$.body[0].checkOutDate").value("2024-01-01"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }


    // [유저] 문의사항 작성
    @Test
    public void question_write_success_test() throws Exception {
        // given
        UserRequest.QuestionSave reqDTO = new UserRequest.QuestionSave();
        reqDTO.setUserId(1);
        reqDTO.setTitle("제목1");
        reqDTO.setContent("내용1");
        reqDTO.setContent("COMPLETION");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/api/users/question")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body").value("문의작성 성공"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }


}
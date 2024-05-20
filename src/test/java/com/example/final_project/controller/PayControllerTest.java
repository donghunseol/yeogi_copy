package com.example.final_project.controller;

import com.example.final_project.MyWithRestDoc;
import com.example.final_project._core.enums.PayEnum;
import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.pay.Pay;
import com.example.final_project.pay.PayRequest;
import com.example.final_project.user.SessionUser;
import com.example.final_project.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.example.final_project._core.enums.PayEnum.REFUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PayControllerTest extends MyWithRestDoc {

    private static ObjectMapper om;
    private static String jwt;

    @BeforeAll
    public static void setup() {
        om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
    }

    @BeforeAll
    public static void setUp() {
        jwt = JwtUtil.userCreate(
                User.builder()
                        .id(1)
                        .email("ssar@nate.com")
                        .password("1234")
                        .build());
    }

    //결제 성공
    @Test
    public void pay_progress_test() throws Exception {
        //given
        Integer payId = 1;
        PayRequest.DTO reqDTO = new PayRequest.DTO();
        reqDTO.setAmount(10000);
        reqDTO.setWay("Credit Card");
        reqDTO.setState(PayEnum.PROCESSING);

        String reqBody = om.writeValueAsString(reqDTO);

        //when
        System.out.println("jwt : "+jwt);
        ResultActions actions = mvc.perform(
                put("/api/reservation/pay/" +payId)
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.amount").value("10000"));
        actions.andExpect(jsonPath("$.body.way").value("Credit Card"));
        actions.andExpect(jsonPath("$.body.state").value("PROCESSING"));
    }

    //결제실패
    @Test
    public void pay_progress_failure_test() throws Exception {
        //given
        Integer payId = 1;
        PayRequest.DTO reqDTO = new PayRequest.DTO();
        reqDTO.setAmount(10000);
        reqDTO.setWay("Credit Card");
        reqDTO.setState(PayEnum.PROCESSING);

        String reqBody = om.writeValueAsString(reqDTO);


        jwt = JwtUtil.userCreate(
                User.builder()
                        .id(2)
                        .email("cos@nate.com")
                        .password("1234")
                        .build());

        //when
        ResultActions actions = mvc.perform(
                put("/api/reservation/pay/" + payId)
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.errorMessage").value("결제를 진행할 권한이 없습니다"));
    }

    //결제 환불 및 예약 취소
    @Test
    public void pay_refund_test() throws Exception {
        //given
        Integer payId = 8;

        //when
        System.out.println("jwt : "+jwt);
        ResultActions actions = mvc.perform(
                put("/api/reservation/refund/" +payId)
                       .header("Authorization", "Bearer " + jwt)

        );
        //then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.id").value("8"));
        actions.andExpect(jsonPath("$.body.reservationId").value("8"));
        actions.andExpect(jsonPath("$.body.state").value("REFUND"));
    }

    //결제 환불 및 예약 취소 실패
    @Test
    public void pay_refund_right_fail_test() throws Exception {
        //given
        Integer payId = 13;


        //when
        System.out.println("jwt : "+jwt);
        ResultActions actions = mvc.perform(
                put("/api/reservation/refund/" +payId)
                        .header("Authorization", "Bearer " + jwt)

        );
        //then
        System.out.println("$.status()");
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.errorMessage").value("예약을 취소할 권한이 없습니다"));

    }

    //결제 환불 및 예약 취소 실패
    @Test
    public void pay_refund_fail_empty_pay_test() throws Exception {
        //given
        Integer payId = 100;


        //when
        System.out.println("jwt : "+jwt);
        ResultActions actions = mvc.perform(
                put("/api/reservation/refund/" +payId)
                        .header("Authorization", "Bearer " + jwt)

        );

        //then
        System.out.println("$.status()");
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.errorMessage").value("존재하지 않는 결제 내역입니다"));

    }

    // 결제 환불 및 예약 취소 실패 - 이미 환불된 경우
    @Test
    public void pay_refund_fail_already_refunded_test() throws Exception {
        //given
        Integer payId = 11;

        jwt = JwtUtil.userCreate(
                User.builder()
                        .id(2)
                        .email("cos@nate.com")
                        .password("1234")
                        .build());

        //when
        ResultActions actions = mvc.perform(
                put("/api/reservation/refund/" + payId)
                        .header("Authorization", "Bearer " + jwt)
        );

        //then
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.errorMessage").value("이미 취소된 예약입니다"));
    }


    // 결제 환불 및 예약 취소 실패 - 이미 환불된 경우
    @Test
    public void pay_refund_fail_already_completion_test() throws Exception {
        //given
        Integer payId = 3;


        //when
        ResultActions actions = mvc.perform(
                put("/api/reservation/refund/" + payId)
                        .header("Authorization", "Bearer " + jwt)
        );

        //then
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.errorMessage").value("이미 이용한 숙소입니다"));
    }
}
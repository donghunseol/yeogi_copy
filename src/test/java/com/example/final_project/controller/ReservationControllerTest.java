package com.example.final_project.controller;

import com.example.final_project.MyWithRestDoc;
import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.reservation.ReservationRequest;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ReservationControllerTest extends MyWithRestDoc {

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
    public void make_reservation_test() throws Exception {
        // given
        Integer roomId = 1;
        ReservationRequest.DTO reqDTO = new ReservationRequest.DTO();
        reqDTO.setUserId(1);
        reqDTO.setRoomId(1);
        reqDTO.setCheckInDate(LocalDate.of(2024, 5, 18));
        reqDTO.setCheckOutDate(LocalDate.of(2024, 5, 19));
        reqDTO.setReservationName("곽두팔");
        reqDTO.setReservationTel("010-1133-5577");

        String reqBody = om.writeValueAsString(reqDTO);
//        System.out.println("reqBody : " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/api/reservation/"+ roomId)
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
        actions.andExpect(jsonPath("$.body.id").value(14));
        actions.andExpect(jsonPath("$.body.userId").value(1));
        actions.andExpect(jsonPath("$.body.roomId").value(1));
        actions.andExpect(jsonPath("$.body.checkInDate").value("2024-05-18"));
        actions.andExpect(jsonPath("$.body.checkOutDate").value("2024-05-19"));
        actions.andExpect(jsonPath("$.body.reservationName").value("곽두팔"));
        actions.andExpect(jsonPath("$.body.reservationTel").value("010-1133-5577"));
        actions.andExpect(jsonPath("$.body.price").value(130000));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}

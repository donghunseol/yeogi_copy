package com.example.final_project.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * 1. 통합테스트 (스프링의 모든 빈을 IoC에 등록하고 테스트 하는 것)
 * 2. 배포 직전 최종 테스트
 */
@AutoConfigureMockMvc // MockMvc IoC 로드
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // 모든 빈 IoC 로드, MOCK로 가짜 서버를 띄운다.
public class UserControllerTest {

    private ObjectMapper om;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
    }


    // 회원 로그인
    // 회원 가입 후 로그인
    @Test
    public void join_test() throws Exception {
        // given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setEmail("code@naver.com");
        reqDTO.setPassword("13579");
        reqDTO.setName("code");
        reqDTO.setPhone("010-1133-5577");
        reqDTO.setBirth(LocalDate.of(2020, 2, 2));

        String reqBody = om.writeValueAsString(reqDTO);
//        System.out.println("reqBody : " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/users/join")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
//        int statusCode = actions.andReturn().getResponse().getStatus();
        System.out.println("respBody : " + respBody);
//        System.out.println("statusCode : " + statusCode);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.id").value(4));
        actions.andExpect(jsonPath("$.body.email").value("code@naver.com"));
        actions.andExpect(jsonPath("$.body.name").value("code"));
        actions.andExpect(jsonPath("$.body.phone").value("010-1133-5577"));
        actions.andExpect(jsonPath("$.body.birth").value("2020-02-02"));
    }

    // 회원가입 시 이메일중복 체크확인
    // 회원 정보 수정
    // 로그인한 회원의 예약 내역 페이지
    // 로그인한 회원의 예약 내역 페이지 - 상세보기
    // 로그인 한 회원의 알림 목록
    // FAQ리스트
    // 객실 별 예약 조회 (달력)
    // [유저] 문의사항 작성


}
package com.example.final_project.controller;

import com.example.final_project.MyWithRestDoc;
import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ScrapControllerTest extends MyWithRestDoc {

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

    // 숙소목록 첫번째 리스트
    @Test
    public void whenGetMyScrapList_thenReturnsListOfScraps() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/api/scrap/my-scraps")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("actions/test : " + responseBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].scrapId").value(1));
        actions.andExpect(jsonPath("$.body[0].stayId").value(1));
        actions.andExpect(jsonPath("$.body[0].stayName").value("호텔 블루 하버"));
        actions.andExpect(jsonPath("$.body[0].stayAddress").value("부산시 해운대구 좌동순환로 99 (좌동)"));
        actions.andExpect(jsonPath("$.body[0].stayIntro").value("해운대 최고의 오션뷰를 자랑하는 숙소!"));
        actions.andExpect(jsonPath("$.body[0].stayImageId").value(1));
        actions.andExpect(jsonPath("$.body[0].stayImageName").value("hotel1.png"));
        actions.andExpect(jsonPath("$.body[0].stayImagePath").value("/images/hotel/hotel1.png"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);

    }

    // 찜 등록
    @Test
    public void whenInsertScrap_thenReturnsSuccessMessage() throws Exception {
        // given
        Integer stayId = 5;

//        String reqBody = om.writeValueAsString(Map.of("stayId", stayId));
//        System.out.println("reqBody = " + reqBody);

        // when
        System.out.println("jwt : "+jwt);
        ResultActions actions = mvc.perform(
                post("/api/scrap/stay/" + stayId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("actions/test : " + responseBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
    // 이미 찜 등록된 숙소
    @Test
    public void whenInsert_scrap_already_message() throws Exception {
        // given
        Integer stayId = 10;

        // when
        System.out.println("jwt : "+jwt);
        ResultActions actions = mvc.perform(
                post("/api/scrap/stay/" + stayId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("actions/test : " + responseBody);

        // then
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.success").value("false"));
        actions.andExpect(jsonPath("$.errorMessage").value("이미 숙소가 찜 되어있습니다"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 숙소 찜 취소
    @Test
    public void whenDeleteScrap_thenReturnsSuccessMessage() throws Exception {

        // given
        Integer stayId = 2;

        // when
        ResultActions actions = mvc.perform(
                delete("/api/scrap/stay/" + stayId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("actions/test : " + responseBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body").value("스크랩 취소 성공"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 숙소 찜 취소
    @Test
    public void when_delete_scrap_delete_already_message() throws Exception {

        // given
        Integer stayId = 6;

        // when
        ResultActions actions = mvc.perform(
                delete("/api/scrap/stay/" + stayId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("actions/test : " + responseBody);

        // then
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.success").value("false"));
        actions.andExpect(jsonPath("$.errorMessage").value("해당 찜목록 를 찾을 수 없습니다"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

}
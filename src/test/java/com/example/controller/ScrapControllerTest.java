package com.example.controller;

import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.scrap.ScrapRepository;
import com.example.final_project.scrap.ScrapRestController;
import com.example.final_project.scrap.ScrapService;
import com.example.final_project.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ScrapRestController.class)
public class ScrapControllerTest{

    private static ObjectMapper om;
    private static String jwt;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScrapService scrapService;

    @MockBean
    private ScrapRepository scrapRepository;

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

    @Test
    public void whenInsertScrap_thenReturnsSuccessMessage() throws Exception {
        // given
        Integer stayId = 3;

//        String reqBody = om.writeValueAsString(Map.of("stayId", stayId));
//        System.out.println("reqBody = " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/api/scrap/stay/" + stayId)
                        .header("Authorization", "Bearer " + jwt)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("actions/test : " + actions.andReturn().getResponse());

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
    }

//    @Test
//    public void whenDeleteScrap_thenReturnsSuccessMessage() throws Exception {
//        // given
//        Integer stayId = 1;
//        String sessionUserJson = "{\"userId\": 1, \"username\": \"ssar\"}";
//
//        // when
//        ResultActions result = mockMvc.perform(delete("/stay/{stayId}", stayId)
//                .header("Authorization", "Bearer " + jwt)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(sessionUserJson));
//
//        // eye
//        String respBody = result.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody : " + respBody);
//
//        // then
//        result.andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").value("스크랩 취소 성공"));
//    }

//    @Test
//    public void whenGetMyScrapList_thenReturnsListOfScraps() throws Exception {
//        // given
//        String sessionUserJson = "{\"userId\": 1, \"username\": \"ssar\"}";
//
//        // when
//        ResultActions result = mockMvc.perform(get("/my-scraps")
//                .header("Authorization", "Bearer " + jwt)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(sessionUserJson));
//
//        // eye
//        String respBody = result.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody : " + respBody);
//
//        // then
//        result.andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").isArray());
//        // Add more assertions as per your response structure
//    }
}

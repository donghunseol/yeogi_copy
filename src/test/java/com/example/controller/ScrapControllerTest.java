package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.scrap.ScrapResponse;
import com.example.final_project.user.SessionUser;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


@AutoConfigureMockMvc
@SpringBootTest(classes = {ScrapControllerTest.class})
public class ScrapControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper om = new ObjectMapper();

    private static String jwt;

    @BeforeAll
    public static void setUp() {
        jwt = JwtUtil.userCreate(
                User.builder()
                        .id(1)
                        .email("ssar@nate.com")
                        .password("1234")
                        .build());
    }

//    @Test
//    public void whenInsertScrap_thenReturnsSuccessMessage() throws Exception {
//        // given
//        Integer stayId = 1;
//
//        String sessionUser = jwt;
//        ScrapResponse.Save reqDTO = new ScrapResponse.Save();
//        reqDTO.setStayId(stayId);
//
//        String respDTO = om.writeValueAsString(respDTO);
//
//
//                // when
//        ResultActions actions  = mockMvc.perform(
//                post("/api/scrap/stay/"+stayId)
//                .header("Authorization", "Bearer " + jwt)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8") // 문자 인코딩 설정
//                        .content(req)
//
//        );


//        // eye
//        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody : " + respBody);
//
//        // then
//        actions.andExpect(jsonPath("$.status").value(200));
//        actions.andExpect(jsonPath("$.msg").value("성공"));
//        actions.andExpect(jsonPath("$.body.username").value("ssar"));
//        actions.andExpect(jsonPath("$.body.email").value("ssar@nate.com"));
//    }

    @Test
    public void whenDeleteScrap_thenReturnsSuccessMessage() throws Exception {
        // given
        Integer stayId = 1;
        String sessionUserJson = "{\"userId\": 1, \"username\": \"ssar\"}";

        // when
        ResultActions result = mockMvc.perform(delete("/stay/{stayId}", stayId)
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sessionUserJson));

        // eye
        String respBody = result.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("스크랩 취소 성공"));
    }

    @Test
    public void whenGetMyScrapList_thenReturnsListOfScraps() throws Exception {
        // given
        String sessionUserJson = "{\"userId\": 1, \"username\": \"ssar\"}";

        // when
        ResultActions result = mockMvc.perform(get("/my-scraps")
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sessionUserJson));

        // eye
        String respBody = result.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
        // Add more assertions as per your response structure
    }
}

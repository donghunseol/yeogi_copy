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

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class RoomControllerTest extends MyWithRestDoc {
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
    public void room_detail_success_test() throws Exception {
        // given
        Integer roomId = 1;

        // when
        ResultActions actions = mvc.perform(
                get("/room/detail/" + roomId )
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.id").value(1));
        actions.andExpect(jsonPath("$.body.imageName").value("room1.jpg"));
        actions.andExpect(jsonPath("$.body.imagePath").value("/images/room1.jpg"));
        actions.andExpect(jsonPath("$.body.tier").value("디럭스"));
        actions.andExpect(jsonPath("$.body.price").value(150000));
        actions.andExpect(jsonPath("$.body.salePrice").value(130000));
        actions.andExpect(jsonPath("$.body.saleState").value("APPLIED"));
        actions.andExpect(jsonPath("$.body.information.minPerson").value(2));
        actions.andExpect(jsonPath("$.body.information.maxPerson").value(3));
        actions.andExpect(jsonPath("$.body.information.announcement").value("스마트앱 체크인만 가능 비대면 체크인, 대면시 추가요금발생  여기어때 발송 입퇴실시간 무관:하이원 발송 시간 확인"));
        actions.andExpect(jsonPath("$.body.information.basicInformation").value("더블베드 1개 객실+욕실/17.24평"));
        actions.andExpect(jsonPath("$.body.information.moreInfo").value("조식 제공"));
        actions.andExpect(jsonPath("$.body.options[0].name").value("수영장"));
        actions.andExpect(jsonPath("$.body.options[0].iconName").value("waterLadder"));
        actions.andExpect(jsonPath("$.body.notice").value("객실 내 취사 금지"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}

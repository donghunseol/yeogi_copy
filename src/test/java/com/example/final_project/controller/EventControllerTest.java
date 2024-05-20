package com.example.final_project.controller;

import com.example.final_project.MyWithRestDoc;
import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EventControllerTest extends MyWithRestDoc {

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
    public void event_list_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/event")
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].id").value(4));
        actions.andExpect(jsonPath("$.body[0].imageName").value("eventTitle4.png"));
        actions.andExpect(jsonPath("$.body[0].imagePath").value("/images/event/event_title/eventTitle4.png"));
        actions.andExpect(jsonPath("$.body[0].startDate").value("2024년 04월 04일"));
        actions.andExpect(jsonPath("$.body[0].endDate").value("2024년 04월 20일"));
        actions.andExpect(jsonPath("$.body[0].state").value("Enable"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void event_detail_success_test() throws Exception {
        // given
        Integer eventId = 1;

        // when
        ResultActions actions = mvc.perform(
                get("/event/detail/"+ eventId)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.id").value(1));
        actions.andExpect(jsonPath("$.body.imageName").value("event1.png"));
        actions.andExpect(jsonPath("$.body.imagePath").value("/images/event/event_content/event1.png"));
        actions.andExpect(jsonPath("$.body.startDate").value("2024년 04월 04일"));
        actions.andExpect(jsonPath("$.body.endDate").value("2024년 04월 15일"));
        actions.andExpect(jsonPath("$.body.state").value("Disable"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void event_detail_fail_test() throws Exception {
        // given
        Integer eventId = 15;

        // when
        ResultActions actions = mvc.perform(
                get("/event/detail/"+ eventId)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.errorMessage").value("해당 이벤트를 찾을 수 없습니다"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}

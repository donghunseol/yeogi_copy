package com.example.controller;

import com.example.MyWithRestDoc;
import com.example.final_project._core.enums.CompanyEnum;
import com.example.final_project.company.*;
import com.example.final_project.reservation.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("기업 API")
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {CompanyController.class, CompanyService.class})
public class CompanyControllerTest extends MyWithRestDoc {
    @Autowired
    private ObjectMapper om = new ObjectMapper();

    @MockBean
    private CompanyService companyService;

    @MockBean
    private CompanyEnum companyEnum;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private ReservationService reservationService;

    private static MockHttpSession session;

    @BeforeAll
    public static void setUp() {
        session = new MockHttpSession();
        SessionCompany sessionCompany = SessionCompany.builder()
                .id(1)
                .email("com1@nate.com")
                .password("1234")
                .businessName("Test Business")
                .businessNumber("1234567890")
                .businessAddress("123 Test St.")
                .phone("123-4567-8910")
                .name("Test Company")
                .state(CompanyEnum.ACTIVE)
                .reportCount(0)
                .createdAt(LocalDateTime.now())
                .build();
        session.setAttribute("sessionUser", sessionCompany);
    }

    @DisplayName("로그인 성공")
    @Test
    public void testLogin() throws Exception {
        // Given
        CompanyRequest.LoginDTO loginDTO = new CompanyRequest.LoginDTO();
        loginDTO.setEmail("com1@nate.com");
        loginDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(loginDTO);

        // When
        ResultActions actions = mvc.perform(post("/company/login")
                .content(reqBody)
                .contentType(MediaType.APPLICATION_JSON)
                .session(session)
        ); // 세션 설정

        // Then
        actions.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/manage/stays"))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }

    @DisplayName("로그인 실패")
    @Test
    public void testLoginFailure() throws Exception {
        // Given
        CompanyRequest.LoginDTO loginDTO = new CompanyRequest.LoginDTO();
        loginDTO.setEmail("csdfsd@nate.com");
        loginDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(loginDTO);

        System.out.println("loginDTO : " + loginDTO);
        System.out.println("reqBody : " + reqBody);

        Mockito.when(companyRepository.findByIdAndPassword(anyString(), anyString()))
                .thenReturn(Optional.empty());

        // When
        ResultActions actions = mvc.perform(post("/company/login")
                .content(reqBody)
                .contentType(MediaType.APPLICATION_JSON)
                .session(session));

        // Then
        actions.andExpect(status().isNotFound());
    }

//    @Test
//    public void testLoginFailure() throws Exception {
//        // Given
//        CompanyRequest.LoginDTO loginDTO = new CompanyRequest.LoginDTO();
//        loginDTO.setEmail("wrong@nate.com");
//        loginDTO.setPassword("wrongpassword");
//
//        String reqBody = om.writeValueAsString(loginDTO);
//
//        // Mocking the service layer to throw an exception for incorrect login details
//        when(companyService.login(eq(loginDTO))).thenThrow(new Exception404("아이디 및 패스워드가 일치하지 않습니다"));
//
//        // Mock session setup
//        MockHttpSession session = new MockHttpSession();
//
//        // When
//        ResultActions actions = mvc.perform(post("/company/login")
//                .content(reqBody)
//                .contentType(MediaType.APPLICATION_JSON)
//                .session(session));
//
//        // Then
//        actions.andExpect(status().isNotFound()); // Expecting a 404 error
//    }


    @Test
    public void testJoinCompany() throws Exception {
        CompanyRequest.JoinDTO joinDTO = new CompanyRequest.JoinDTO();
        joinDTO.setEmail("newcompany@example.com");
        joinDTO.setPassword("newpassword");
        joinDTO.setBusinessName("New Business");
        joinDTO.setBusinessNumber("0987654321");
        joinDTO.setBusinessAddress("456 New St.");
        joinDTO.setPhone("098-765-4321");
        joinDTO.setName("New Company");

        String reqBody = om.writeValueAsString(joinDTO);

        ResultActions actions = mvc.perform(post("/company/join").content(reqBody).contentType(MediaType.APPLICATION_JSON));

        actions.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/company"));
    }

    @Test
    public void testLogout() throws Exception {
        ResultActions actions = mvc.perform(get("/logout").session(session));

        actions.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/company"));
    }

//    @Test
//    public void testUpdateCompany() throws Exception {
//        CompanyRequest.UpdateDTO updateDTO = new CompanyRequest.UpdateDTO();
//        updateDTO.setEmail("updatedcompany@example.com");
//        updateDTO.setPassword("updatedpassword");
//        updateDTO.setBusinessName("Updated Business");
//        updateDTO.setBusinessNumber("1122334455");
//        updateDTO.setBusinessAddress("789 Updated St.");
//        updateDTO.setPhone("111-222-3333");
//        updateDTO.setName("Updated Company");
//
//        String reqBody = om.writeValueAsString(updateDTO);
//
//        ResultActions actions = mvc.perform(post("/information/update/1")
//                .content(reqBody)
//                .contentType(MediaType.APPLICATION_JSON)
//                .session(session));
//
//        actions.andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/manage/stays"));
//    }

//    @Test
//    public void testDeleteCompany() throws Exception {
//        CompanyRequest.DeleteDTO deleteDTO = new CompanyRequest.DeleteDTO();
//        deleteDTO.setPassword("password");
//
//        String reqBody = om.writeValueAsString(deleteDTO);
//
//        ResultActions actions = mvc.perform(post("/company/delete/1")
//                .content(reqBody)
//                .contentType(MediaType.APPLICATION_JSON)
//                .session(session));
//
//        actions.andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/company"));
//    }

    @Test
    public void testCompanyStayList() throws Exception {
        ResultActions actions = mvc.perform(get("/manage/stays").session(session));

        actions.andExpect(status().isOk()).andExpect(view().name("/company/stay/main")).andExpect(model().attributeExists("stayList"));
    }

    @Test
    public void testCompanyRoomList() throws Exception {
        ResultActions actions = mvc.perform(get("/manage/stays/1/rooms").session(session));

        actions.andExpect(status().isOk()).andExpect(view().name("/company/stay/detail")).andExpect(model().attributeExists("stayList")).andExpect(model().attributeExists("detailList"));
    }

    @Test
    public void testCompanyReservationDetail() throws Exception {
        ResultActions actions = mvc.perform(get("/reservations/1/detail").session(session));

        actions.andExpect(status().isOk()).andExpect(view().name("/company/reservation/detail")).andExpect(model().attributeExists("reservationDetail"));
    }

    @Test
    public void testCompanyRevenue() throws Exception {
        ResultActions actions = mvc.perform(get("/revenue").session(session));

        actions.andExpect(status().isOk()).andExpect(view().name("/company/revenue/main")).andExpect(model().attributeExists("stayCount")).andExpect(model().attributeExists("totalIncome")).andExpect(model().attributeExists("stayTotalIncomeList"));
    }

    @Test
    public void testCompReservationList() throws Exception {
        ResultActions actions = mvc.perform(get("/reservations/status").session(session));

        actions.andExpect(status().isOk()).andExpect(view().name("/company/reservation/main")).andExpect(model().attributeExists("reservationCount")).andExpect(model().attributeExists("reservationList"));
    }

    @Test
    public void testCompReservationDetail() throws Exception {
        ResultActions actions = mvc.perform(get("/reservations/1").session(session));

        actions.andExpect(status().isOk()).andExpect(view().name("/company/reservation/main-detail")).andExpect(model().attributeExists("reservationDetail"));
    }
}

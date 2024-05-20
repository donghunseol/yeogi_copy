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

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class StayControllerTest extends MyWithRestDoc {

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
    // 특가리스트
    @Test
    public void sale_stay_list() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/stays/sale")

        );
        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].stayId").value("1"));
        actions.andExpect(jsonPath("$.body[0].imageName").value("hotel1.png"));
        actions.andExpect(jsonPath("$.body[0].imagePath").value("/images/hotel/hotel1.png"));
        actions.andExpect(jsonPath("$.body[0].name").value("호텔 블루 하버"));
        actions.andExpect(jsonPath("$.body[0].address").value("부산시 해운대구 좌동순환로 99 (좌동)"));
        actions.andExpect(jsonPath("$.body[0].intro").value("해운대 최고의 오션뷰를 자랑하는 숙소!"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 해외리스트
    @Test
    public void oversea_stay_list() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/stays/oversea")

        );
        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].stayId").value("4"));
        actions.andExpect(jsonPath("$.body[0].imageName").value("overseas1.png"));
        actions.andExpect(jsonPath("$.body[0].imagePath").value("/images/overseas/overseas1.png"));
        actions.andExpect(jsonPath("$.body[0].name").value("프리미어 캐빈-오사카"));
        actions.andExpect(jsonPath("$.body[0].address").value("오사카 신사이바시"));
        actions.andExpect(jsonPath("$.body[0].intro").value("텐진역에서 5분거리에 위치한 숙소"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 호텔리스트
    @Test
    public void hotel_stay_list() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/stays/hotel")

        );
        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].stayId").value("1"));
        actions.andExpect(jsonPath("$.body[0].imageName").value("hotel1.png"));
        actions.andExpect(jsonPath("$.body[0].imagePath").value("/images/hotel/hotel1.png"));
        actions.andExpect(jsonPath("$.body[0].name").value("호텔 블루 하버"));
        actions.andExpect(jsonPath("$.body[0].address").value("부산시 해운대구 좌동순환로 99 (좌동)"));
        actions.andExpect(jsonPath("$.body[0].intro").value("해운대 최고의 오션뷰를 자랑하는 숙소!"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 캠핑리스트
    @Test
    public void camping_stay_list() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/stays/camping")

        );
        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].stayId").value("8"));
        actions.andExpect(jsonPath("$.body[0].imageName").value("camping1.png"));
        actions.andExpect(jsonPath("$.body[0].imagePath").value("/images/camping/camping1.png"));
        actions.andExpect(jsonPath("$.body[0].name").value("캠핑 체험 랜드"));
        actions.andExpect(jsonPath("$.body[0].address").value("강원도 평창군 봉평면 대정로 253"));
        actions.andExpect(jsonPath("$.body[0].intro").value("자연 속에서 즐기는 힐링 캠핑"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 모텔리스트
    @Test
    public void motel_stay_list() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/stays/motel")

        );
        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].stayId").value("13"));
        actions.andExpect(jsonPath("$.body[0].imageName").value("motel2.png"));
        actions.andExpect(jsonPath("$.body[0].imagePath").value("/images/motel/motel2.png"));
        actions.andExpect(jsonPath("$.body[0].name").value("모텔 더 포레스트"));
        actions.andExpect(jsonPath("$.body[0].address").value("서울특별시 강남구 도곡로 156"));
        actions.andExpect(jsonPath("$.body[0].intro").value("강남의 편안한 모텔"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 팬션리스트
    @Test
    public void pension_stay_list() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/stays/pension")

        );
        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].stayId").value("18"));
        actions.andExpect(jsonPath("$.body[0].imageName").value("pension3.png"));
        actions.andExpect(jsonPath("$.body[0].imagePath").value("/images/pension/pension3.png"));
        actions.andExpect(jsonPath("$.body[0].name").value("별빛 펜션"));
        actions.andExpect(jsonPath("$.body[0].address").value("강원도 강릉시 경강로 3104"));
        actions.andExpect(jsonPath("$.body[0].intro").value("강릉의 아름다운 풍경을 감상하는 펜션"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 홈&빌라리스트
    @Test
    public void home_and_villa_stay_list() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/stays/home-and-villa")

        );
        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].stayId").value("23"));
        actions.andExpect(jsonPath("$.body[0].imageName").value("homeAndVilla2.png"));
        actions.andExpect(jsonPath("$.body[0].imagePath").value("/images/home_and_villa/homeAndVilla2.png"));
        actions.andExpect(jsonPath("$.body[0].name").value("럭셔리 빌라"));
        actions.andExpect(jsonPath("$.body[0].address").value("서울특별시 용산구 우사단로 54"));
        actions.andExpect(jsonPath("$.body[0].intro").value("용산의 프라이빗한 럭셔리 빌라"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 게하리스트
    @Test
    public void guesthouse_stay_list() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/stays/guesthouse")

        );
        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].stayId").value("28"));
        actions.andExpect(jsonPath("$.body[0].imageName").value("guestHouse2.png"));
        actions.andExpect(jsonPath("$.body[0].imagePath").value("/images/guest_house/guestHouse2.png"));
        actions.andExpect(jsonPath("$.body[0].name").value("친구랑 편한 게스트하우스"));
        actions.andExpect(jsonPath("$.body[0].address").value("서울특별시 마포구 와우산로 109"));
        actions.andExpect(jsonPath("$.body[0].intro").value("마포에서 편안한 하룻밤을 보내세요"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 게하리스트
    @Test
    public void search_stay() throws Exception {
        // given
        String stayName = "호텔 미라클랜드";
        String stayAddress = "서울";
        String roomPrice = "1800000";
        ResultActions actions = mvc.perform(
                get("/stay/search")
                        .param("stayName", stayName)
                        .param("stayAddress", stayAddress)
                        .param("roomPrice", roomPrice.toString())

        );
        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].id").value("3"));
        actions.andExpect(jsonPath("$.body[0].name").value("호텔 미라클랜드"));
        actions.andExpect(jsonPath("$.body[0].address").value("서울시 중구 장충동2가 300"));
        actions.andExpect(jsonPath("$.body[0].imageName").value("hotel3.png"));
        actions.andExpect(jsonPath("$.body[0].imagePath").value("/images/hotel/hotel3.png"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 메인페이지
    @Test
    public void main_home() throws Exception {
        //given

        //when
        ResultActions actions = mvc.perform(
                get("/home")

        );
        //then

        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.specialprices[0].stayId").value("1"));
        actions.andExpect(jsonPath("$.body.specialprices[0].imageName").value("hotel1.png"));
        actions.andExpect(jsonPath("$.body.specialprices[0].imagePath").value("/images/hotel/hotel1.png"));
        actions.andExpect(jsonPath("$.body.specialprices[0].name").value("호텔 블루 하버"));
        actions.andExpect(jsonPath("$.body.domestics[0].stayId").value("1"));
        actions.andExpect(jsonPath("$.body.domestics[0].imageName").value("hotel1.png"));
        actions.andExpect(jsonPath("$.body.domestics[0].imagePath").value("/images/hotel/hotel1.png"));
        actions.andExpect(jsonPath("$.body.domestics[0].name").value("호텔 블루 하버"));
        actions.andExpect(jsonPath("$.body.overseas[0].stayId").value("4"));
        actions.andExpect(jsonPath("$.body.overseas[0].imageName").value("overseas1.png"));
        actions.andExpect(jsonPath("$.body.overseas[0].imagePath").value("/images/overseas/overseas1.png"));
        actions.andExpect(jsonPath("$.body.overseas[0].name").value("프리미어 캐빈-오사카"));
        actions.andExpect(jsonPath("$.body.events[0].id").value("4"));
        actions.andExpect(jsonPath("$.body.events[0].name").value("인기 호텔 최대 5만원할인"));
        actions.andExpect(jsonPath("$.body.events[0].imageName").value("eventTitle4.png"));
        actions.andExpect(jsonPath("$.body.events[0].imagePath").value("/images/event/event_title/eventTitle4.png"));
        actions.andExpect(jsonPath("$.body.events[0].startDate").value("2024년 04월 04일"));
        actions.andExpect(jsonPath("$.body.events[0].endDate").value("2024년 04월 04일"));
        actions.andExpect(jsonPath("$.body.events[0].state").value("Enable"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    //숙소 상세보기

    @Test
    public void stay_detail() throws Exception {
        //given
        Integer stayId = 1;
        //when
        ResultActions actions = mvc.perform(
                get("/stays/" + stayId)

        );
        //then

        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.stayContents.stay.stayId").value("1"));
        actions.andExpect(jsonPath("$.body.stayContents.stay.stayName").value("호텔 블루 하버"));
        actions.andExpect(jsonPath("$.body.stayContents.stay.information").value("객실 내 취사 금지"));
        actions.andExpect(jsonPath("$.body.stayContents.stay.intro").value("해운대 최고의 오션뷰를 자랑하는 숙소!"));
        actions.andExpect(jsonPath("$.body.stayContents.stay.address").value("부산시 해운대구 좌동순환로 99 (좌동)"));
        actions.andExpect(jsonPath("$.body.stayContents.stay.latitude").value("35.1696797"));
        actions.andExpect(jsonPath("$.body.stayContents.stay.longitude").value("129.1745542"));
        actions.andExpect(jsonPath("$.body.stayContents.stayImageList[0].stayImageId").value("1"));
        actions.andExpect(jsonPath("$.body.stayContents.stayImageList[0].stayImagePath").value("/images/hotel/hotel1.png"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewId").value("1"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].userId").value("1"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewId").value("1"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].userName").value("김쌀"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].stayId").value("1"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewScore").value("5"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewContent").value("정말 좋았어요!"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].isDelete").value("FLAWLESS"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewParentId").value(nullValue()));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewParentId").value(nullValue()));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewChildrenList[0].reviewId").value("7"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewChildrenList[0].userId").value("1"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewChildrenList[0].userName").value("김회사"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewChildrenList[0].stayId").value("1"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewChildrenList[0].reviewScore").value(nullValue()));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewChildrenList[0].reviewContent").value("감사합니다!"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewChildrenList[0].isDelete").value("FLAWLESS"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewChildrenList[0].reviewParentId").value("1"));
        actions.andExpect(jsonPath("$.body.stayContents.reviewList[0].reviewChildrenList[0].reviewChildrenList").isArray());
        actions.andExpect(jsonPath("$.body.stayContents.optionList[0].optionId").value("5"));
        actions.andExpect(jsonPath("$.body.stayContents.optionList[0].name").value("수영장"));
        actions.andExpect(jsonPath("$.body.stayContents.optionList[0].iconName").value("waterLadder"));
        actions.andExpect(jsonPath("$.body.roomContents[0].roomId").value("1"));
        actions.andExpect(jsonPath("$.body.roomContents[0].roomName").value("스위트룸"));
        actions.andExpect(jsonPath("$.body.roomContents[0].roomTier").value("Deluxe"));
        actions.andExpect(jsonPath("$.body.roomContents[0].roomPrice").value("150000"));
        actions.andExpect(jsonPath("$.body.roomContents[0].roomSpecialState").value("APPLIED"));
        actions.andExpect(jsonPath("$.body.roomContents[0].roomSpecialPrice").value("130000"));
        actions.andExpect(jsonPath("$.body.roomContents[0].roomImagePath").value("/images/room1.jpg"));
        actions.andExpect(jsonPath("$.body.roomContents[0].checkInTime").value("15:00:00"));
        actions.andExpect(jsonPath("$.body.roomContents[0].checkOutTime").value("11:00:00"));
    }
}

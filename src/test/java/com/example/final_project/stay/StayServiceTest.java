package com.example.final_project.stay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class StayServiceTest {
    @Autowired
    private StayService stayService;

    @Test
    public void getSearchStayList_test() {
        // given
        StayRequest.SearchDTO reqDTO = StayRequest.SearchDTO.builder()
                .person(4)
                .checkInDate(LocalDate.of(2024, 5, 24))
                .checkOutDate(LocalDate.of(2024, 5, 30))
                .build();

        // when
//        List<StayResponse.SearchListDTO> stays = stayService.getSearchStayList(reqDTO);

        // eye
//        for (StayResponse.SearchListDTO stay : stays) {
//            // System.out.println("getSearchStayList_test/stay/id : " + stay.getId());
//            // System.out.println("getSearchStayList_test/stay/name : " + stay.getName());
//            // System.out.println("getSearchStayList_test/stay/intro : " + stay.getIntro());
//            System.out.println("getSearchStayList_test/stay : " + stay);
//        }

        // then

    }

    @Test

    public void findSpecialListByRoomEnum_test() {
        // given

        // when
        List<StayResponse.SpecialpriceList> respDTO = stayService.findSpecialListByRoom();

        // eye
        System.out.println("findSpecialListByRoomEnum_test/list" + respDTO);

        // then
    }

    @Test
    public void findStayDetail_test(){
        // given
        Integer stayId = 1;

        // when
        StayResponse.StayDetail respDTO = stayService.findStayDetail(stayId);

        // eye
        System.out.println("findStayDetail_test roomContentsDTOS.size : " + respDTO.getRoomContents().size());
        System.out.println("findStayDetail_test roomContentsDTOS.getFirst : " + respDTO.getRoomContents().getFirst());

        // then

    }
}

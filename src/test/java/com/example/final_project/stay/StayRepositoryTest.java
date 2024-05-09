package com.example.final_project.stay;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class StayRepositoryTest {
    @Autowired
    StayRepository stayRepository;

    @Test
    public void findByCompanyId_test() {
        // given
        Integer companyId = 1;

        // when
        List<Stay> stayList = stayRepository.findByCompanyId(companyId);

        // eye
        System.out.println("findByCompanyIdWithOptionsAndRooms_test size : " + stayList.size());

        // then
        Assertions.assertThat(stayList.getFirst().getName()).isEqualTo("호텔 블루 하버");
    }

    // 검색 쿼리 테스트
    @Test
    public void findBySearchStay_test() {
        // given
        LocalDate startDate = LocalDate.of(2024, 5, 30);
        LocalDate endDate = LocalDate.of(2024, 6, 11);
        StayRequest.SearchDTO reqDTO = StayRequest.SearchDTO.builder()
                .checkInDate(startDate)
                .checkOutDate(endDate)
                .build();

        // when
        List<Stay> stays = stayRepository.findBySearchStay(reqDTO.getName(),
                reqDTO.getAddress(),
                reqDTO.getPrice(),
                reqDTO.getPerson());

        // eye
        System.out.println("findBySearchStay_test/size : " + stays.size());
        stays.forEach(stay -> System.out.println("findBySearchStay_test : " + stay.getName()));

        // then

    }

    //전체 숙소 테스트
    @Test
    public void findAll_test() {
            // given

            // when
            List<Stay> stays = stayRepository.findAll();
            // eye
            System.out.println(stays.size());
            // then
        }


//    // 검색 이름 쿼리 테스트
//    @Test
//    public void findByStayName_test() {
//        // given
//        String nameTest1 = "호텔";
//        String nameTest2 = "미라클";
//
//        // when
//        Optional<List<Stay>> staysHotel = stayRepository.findByStayName(nameTest1);
//        Optional<List<Stay>> staysMiracle = stayRepository.findByStayName(nameTest2);
//
//        // eye
//        for (Stay stay : staysHotel.get()) {
//            System.out.println("findByStayName_test/hotel/name : " + stay.getName());
//        }
//
//        for (Stay stay : staysMiracle.get()) {
//            System.out.println("findByStayName_test/miracle/name : " + stay.getName());
//        }
//
//        // then
//    }
//
//    // 검색 지역 쿼리 테스트
//    @Test
//    public void findByStayArea_test() {
//        // given
//        String areaTest1 = "서울";
//        String areaTest2 = "부산";
//
//        // when
//        Optional<List<Stay>> staysSeoul = stayRepository.findByStayArea(areaTest1);
//        Optional<List<Stay>> staysBusan = stayRepository.findByStayArea(areaTest2);
//
//        // eye
//        for (Stay stay : staysSeoul.get()) {
//            System.out.println("findByStayName_test/seoul/name : " + stay.getAddress());
//        }
//
//        for (Stay stay : staysBusan.get()) {
//            System.out.println("findByStayName_test/busan/name : " + stay.getAddress());
//        }
//
//        // then
//    }
}

package com.example.final_project._core.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GpsUtilTest {

    @Test
    public void getLatLng_test(){
        // given
//        String address = "경기도 가평군 설악면 금강로 1266";
        List<String> addressList = Arrays.asList(
                "부산시 해운대구 좌동순환로 99 (좌동)",
                "경상남도 창원시 원이대로 998 (상남동)",
                "서울시 중구 장충동2가 300",
                "오사카 신사이바시",
                "278 Vo Nguyen Giap Street, My An Ward, Ngu Hanh Son District, Da Nang",
                "36 - 38 Lam Hoanh, 프억 미, 다낭, 베트남, 550000",
                "3-4-24 Watanabedouri, Chuo-ku, 텐진, 후쿠오카",
                "강원도 평창군 봉평면 대정로 253",
                "전라남도 순천시 구룡포길 80-14",
                "충청북도 제천시 양평면 목재로 148",
                "경기도 양평군 강상면 행목길 29-21",
                "경기도 가평군 설악면 금강로 1266",
                "서울특별시 강남구 도곡로 156",
                "서울특별시 마포구 와우산로 71",
                "대전광역시 중구 중앙로 123",
                "경기도 수원시 팔달구 권광로 190",
                "경상북도 경주시 보문로 376",
                "강원도 강릉시 경강로 3104",
                "전라남도 순천시 화양면 호수로 55",
                "경상남도 통영시 죽림면 세마해안로 176-8",
                "제주특별자치도 서귀포시 안덕면 상창로 25",
                "충청북도 충주시 산척면 매곡로 66",
                "서울특별시 용산구 우사단로 54",
                "서울특별시 마포구 와우산로 147",
                "경기도 가평군 청평면 청평로 311",
                "강원도 춘천시 국원로 228",
                "경상북도 안동시 하회로 1길 7",
                "서울특별시 마포구 와우산로 109",
                "서울특별시 서대문구 연희로 4길 22",
                "경기도 수원시 팔달구 경수대로 1035",
                "경상남도 창원시 성산구 대방로 89",
                "전라남도 담양군 금성면 비로산길 59-49",
                "강원도 인제군 북면 평대리 109-7",
                "전라북도 정읍시 북면 태조로 472-20",
                "경기도 포천시 일동면 광릉수목원로 188",
                "경기도 양평군 강하면 양평로 434",
                "충청남도 예산군 대술면 여름별로 123",
                "서울특별시 서초구 서초대로 412",
                "부산광역시 해운대구 해운대로 320",
                "대구광역시 수성구 무학로 107",
                "대전광역시 서구 관저동로 87",
                "광주광역시 동구 충장로 3",
                "강원도 화천군 사내면 영춘로 8-24",
                "전라북도 김제시 요촌면 김제영월로 139-12",
                "경기도 수원시 영통구 영통로 97",
                "경기도 양평군 서종면 하산골길 137",
                "충청남도 보령시 미산면 청소로 36",
                "제주특별자치도 서귀포시 성산읍 시흥하동로 409",
                "경기도 용인시 처인구 이동면 양지로 301",
                "강원도 춘천시 서면 서동천로 32",
                "전라남도 완도군 완도읍 완도로 100",
                "부산광역시 해운대구 우동 우동해안로 298",
                "서울특별시 강남구 역삼로 123",
                "인천광역시 중구 해안남로 34",
                "대구광역시 중구 동성로 38",
                "광주광역시 서구 서문대로 817",
                "경기도 수원시 장안구 경수대로 924"
        );

        // when
        List<double[]> resultList = addressList.stream().map(GpsUtil::getLatLng).toList();

        // eye
        for (double[] gps : resultList) {
            System.out.println(Arrays.toString(gps));
        }

        // then


    }

    @Test
    public void getAddress_test(){
        // given
        double[] latAndLon = new double[]{37.5665851, 126.9782038};

        // when
        String result = GpsUtil.getAddress(latAndLon[0], latAndLon[1]);

        // eye
        System.out.println("getAddress_test result : " + result);

        // then


    }
}

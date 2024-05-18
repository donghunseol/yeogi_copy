package com.example.final_project.stay;

import com.example.final_project._core.enums.StayEnum;
import com.example.final_project._core.utils.GpsUtil;
import com.example.final_project.company.Company;
import com.example.final_project.stay_image.StayImage;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StayRequest {

    // 검색 요청 DTO
    @Data
    public static class SearchDTO {
        private String name; // 숙소 이름
        private String address; // 숙소 지역
        private Integer price; // 숙소 가격
        private Integer person; // 인원 수
        private LocalDate checkInDate; // 입실 날짜
        private LocalDate checkOutDate; // 퇴실 날짜

        @Builder
        public SearchDTO(String name, String address, Integer price, Integer person, LocalDate checkInDate, LocalDate checkOutDate) {
            this.name = name;
            this.address = address;
            this.price = price;
            this.person = person;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
        }
    }

    //숙소등록 DTO
    @Data
    public static class SaveDTO {
        private Integer companyId;
        private String name;
        private String category;
        private String address;
        private Double gpsLat; // GPS 좌표(위도)
        private Double gpsLng; // GPS 좌표(경도)
        private StayEnum state;
        private String intro;
        private String information;
        private List<String> options;
        private List<MultipartFile> imgFiles;

        public Stay toEntity(Company company) {
            return Stay.builder()
                    .name(name)
                    .category(category)
                    .address(address)
                    .gpsLat(GpsUtil.getLatLng(address)[0])
                    .gpsLng(GpsUtil.getLatLng(address)[1])
                    .state(state)
                    .intro(intro)
                    .information(information)
                    .company(company)
                    .build();
        }
    }

    // 숙소 수정 DTO
    @Data
    public static class UpdateDTO {
        private String intro;
        private String information;
        private List<String> options;

    }

}

package com.example.final_project.stay;

import com.example.final_project._core.enums.StayEnum;
import com.example.final_project.company.Company;
import com.example.final_project.option.Option;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StayRequest {

    // 검색 요청 DTO
    @Data
    public static class SearchDTO {
        private String name; // 숙소 이름
        private String address; // 숙소 지역
        private String state; // 숙소 예약 여부
        private Integer price; // 숙소 가격
        private LocalDate checkIn; // 입실 시간
        private LocalDate checkOut; // 퇴실 시간
        private Integer minPerson; // 최소 인원
        private Integer maxPerson; // 최대 인원

        @Builder
        public SearchDTO(String name, String address, String state, Integer price, LocalDate checkIn, LocalDate checkOut, Integer minPerson, Integer maxPerson) {
            this.name = name;
            this.address = address;
            this.state = state;
            this.price = price;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.minPerson = minPerson;
            this.maxPerson = maxPerson;
        }
    }

    //숙소등록
    @Data
    public static class SaveDTO {
        private Integer companyId;
        private String name;
        private String category;
        private String address;
        private String intro;
        private String information;
        private List<Option> optionList;
        private LocalDateTime createdAt;

        public Stay toEntity(Company company) {
            return Stay.builder()
                    .name(name)
                    .category(category)
                    .address(address)
                    .intro(intro)
                    .information(information)
                    .createdAt(createdAt)
                    .company(company)
                    .options(optionList)
                    .build();
        }
    }

    // 숙소 수정
    // 이름,분류,주소 등은 삭제 후 등록하는게 맞는거 같아서 뺐어요!!
    @Data
    public static class UpdateDTO {
        private String intro;
        private String information;
        private List<Option> optionList;
    }

    //숙소삭제
    @Data
    public static class DeleteDTO {
        private StayEnum state;
    }
}

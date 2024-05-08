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
        private String name; // 숙소 이름 v
        private String address; // 숙소 지역 v
        private Integer price; // 숙소 가격 v
        private Integer person; // 인원 수 V
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

    //숙소등록
    @Data
    public static class SaveDTO {
        private Integer companyId;
        private String name;
        private String category;
        private String address;
        private String intro;
        private String information;
        private List<String> options;
        private StayEnum state;
        private LocalDateTime createdAt;

        public Stay toEntity(Company company) {
            return Stay.builder()
                    .state(state)
                    .name(name)
                    .category(category)
                    .address(address)
                    .intro(intro)
                    .information(information)
                    .createdAt(createdAt)
                    .company(company)
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

}

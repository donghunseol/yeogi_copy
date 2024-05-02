package com.example.final_project.stay;

import com.example.final_project._core.enums.StayEnum;
import com.example.final_project.option.Option;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StayResponse {

    // 숙소 검색 응답 DTO
    @Data
    public static class SearchListDTO {
        private Integer id; // 숙소 번호
        private String name; // 숙소 이름
        private String address; // 숙소 주소
        private String intro; // 숙소 소개

        public SearchListDTO(Stay stay) {
            this.id = stay.getId();
            this.name = stay.getName();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
        }
    }

    // 숙소등록 응답DTO
    @Data
    public static class Save {
        private Integer companyId;
        private String name;
        private String category;
        private String address;
        private String intro;
        private String information;
        private List<OptionDTO> optionList;
        private LocalDateTime createdAt;

        @Builder
        public Save(Stay stay, List<Option> optionList) {
            this.companyId = stay.getCompany().getId();
            this.name = stay.getName();
            this.category = stay.getCategory();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
            this.information = stay.getInformation();
            this.createdAt = stay.getCreatedAt();
            this.optionList = optionList.stream()
                    .map(OptionDTO::new)
                    .collect(Collectors.toList());
        }

        @Data
        public static class OptionDTO {
            private String name;
            private String iconName;

            public OptionDTO(Option option) {
                this.name = option.getName();
                this.iconName = option.getIconName();
            }
        }
    }

    // 숙소수정Form 응답DTO
    @Data
    public static class UpdateForm {
        private String intro;
        private String information;
        private List<OptionDTO> optionList;
        private LocalDateTime createdAt;

        public UpdateForm(Stay stay, List<Option> optionList) {
            this.intro = stay.getIntro();
            this.information = stay.getInformation();
            this.createdAt = stay.getCreatedAt();
            this.optionList = optionList.stream()
                    .map(OptionDTO::new)
                    .collect(Collectors.toList());
        }

        @Data
        public static class OptionDTO {
            private String name;
            private String iconName;

            public OptionDTO(Option option) {
                this.name = option.getName();
                this.iconName = option.getIconName();
            }
        }
    }

    // 숙소수정 응답DTO
    @Data
    public static class Update {
        private String intro;
        private String information;
        private List<OptionDTO> optionList;
        private LocalDateTime createdAt;

        public Update(Stay stay) {
            this.intro = stay.getIntro();
            this.information = stay.getInformation();
            this.optionList = stay.getOptions().stream().map(OptionDTO::new).toList();
            this.createdAt = stay.getCreatedAt();
        }

        @Data
        public static class OptionDTO {
            private String name;
            private String iconName;

            public OptionDTO(Option option) {
                this.name = option.getName();
                this.iconName = option.getIconName();
            }

        }
    }

    // 숙소삭제 응답DTO
    @Data
    public static class Delete {
        private StayEnum state;

        public Delete(Stay stay) {
            this.state = stay.getState();
        }
    }
}
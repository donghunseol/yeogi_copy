package com.example.final_project.stay;

import com.example.final_project.company.Company;
import com.example.final_project.option.Option;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StayResponse {

    // 숙소등록
    @Data
    public static class Save {
        private Integer companyId;
        private String name;
        private String category;
        private String address;
        private String intro;
        private String information;
        private String imageName;
        private String imagePath;
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
            this.imageName = imageName;
            this.imagePath = imagePath;
            this.createdAt = stay.getCreatedAt();
            this.optionList = optionList.stream()
                    .map(OptionDTO::new)
                    .collect(Collectors.toList());
        }

        @Data
        public static class OptionDTO{
            private String name;
            private String iconName;
            public OptionDTO(Option option) {
                this.name = option.getName();
                this.iconName = option.getIconName();
            }
        }
    }
}
package com.example.final_project.stay;

import com.example.final_project._core.enums.StayEnum;
import com.example.final_project.option.Option;
import com.example.final_project.option.OptionResponse;
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


    //숙소 수정 폼 DTO
    @Data
    public static class UpdateFormDTO{
        private Integer id;
        private String name;
        private String category;
        private List<OptionChekedDTO> options;
        private String address;
        private String intro;
        private String information;

        public UpdateFormDTO(Stay stay, List<OptionChekedDTO> options) {
            this.id = stay.getId();
            this.name = stay.getName();
            this.category = stay.getCategory();
            this.options = options;
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
            this.information = stay.getInformation();
        }

        @Data
        public static class OptionChekedDTO{
            private boolean 피트니스 = false;
            private boolean 침대 = false;
            private boolean 미니바 = false;
            private boolean 수영장 = false;
            private boolean 오션뷰 = false;
            private boolean 와이파이 = false;

            public OptionChekedDTO(List<Option> optionNames) {
                for (Option option : optionNames){
                    if (option.getName().equals("피트니스")){
                        this.피트니스 = true;
                    } else if (option.getName().equals("침대")) {
                        this.침대 = true;
                    } else if (option.getName().equals("미니바")) {
                        this.미니바 = true;
                    } else if (option.getName().equals("수영장")) {
                        this.수영장 = true;
                    } else if (option.getName().equals("오션뷰")) {
                        this.오션뷰 = true;
                    } else if (option.getName().equals("와이파이")) {
                        this.와이파이 = true;
                    }

                }

            }
        }
    }


    // 숙소수정Form 응답DTO
    @Data
    public static class UpdateForm {
        private Integer stayId;
        private String intro;
        private String information;
        private List<OptionDTO> optionList;
        private LocalDateTime createdAt;

        public UpdateForm(Stay stay, List<Option> optionList) {
            this.stayId = stay.getId();
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
        private Integer stayId;
        private String intro;
        private String information;
        private List<OptionDTO> optionList;
        private LocalDateTime createdAt;

        public Update(Stay stay) {
            this.stayId = stay.getId();
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
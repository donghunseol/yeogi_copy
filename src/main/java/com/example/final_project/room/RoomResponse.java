package com.example.final_project.room;

import com.example.final_project._core.enums.RoomEnum;
import com.example.final_project.option.Option;
import com.example.final_project.room_information.RoomInformation;
import com.example.final_project.stay.Stay;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class RoomResponse {

    // 객실 등록 폼 응답DTO
    @Data
    public static class StayId{
        private Integer stayId;

        @Builder
        public StayId(Stay stay) {
            this.stayId = stay.getId();
        }
    }

    // 객실 등록 응답DTO
    @Data
    public static class Save{
        private String roomNumber;
        private Integer minPerson;
        private Integer maxPerson;
        private String moreInfo;
        private Integer price;
        private Integer specialPrice;
        private LocalTime checkIn;
        private LocalTime checkOut;
        private RoomEnum specialState;
        private LocalDateTime createdAt;

        @Builder
        public Save(Room room, RoomInformation roomInformation) {
            this.roomNumber = room.getRoomNumber();
            this.minPerson = roomInformation.getMinPerson();
            this.maxPerson = roomInformation.getMaxPerson();
            this.moreInfo = roomInformation.getMoreInfo();
            this.price = room.getPrice();
            this.specialPrice = room.getSpecialPrice();
            this.checkIn = roomInformation.getCheckIn();
            this.checkOut = roomInformation.getCheckOut();
            this.specialState = room.getSpecialState();
            this.createdAt = room.getCreatedAt();
        }

    }

    // 객실 디테일 응답 DTO
    @Data
    public static class Detail{
        private Integer id;
        private String imageName;
        private String imagePath;
        private String tier;
        private Integer price;
        private Integer salePrice;
        private RoomEnum saleState;
        private RoomInfoDTO information;
        private List<StayOptionDTO> options;

        public Detail(Room room, RoomInfoDTO roomInfoDTO, List<StayOptionDTO> stayOptionDTO) {
            this.id = room.getId();
            this.imageName = room.getImageName();
            this.imagePath = room.getImagePath();
            this.tier = room.getTier();
            this.price = room.getPrice();
            this.salePrice = room.getSpecialPrice();
            this.saleState = room.getSpecialState();
            this.information = roomInfoDTO;
            this.options = stayOptionDTO;
        }

        @Data
        public static class RoomInfoDTO{
            private Integer minPerson;
            private Integer maxPerson;
            private String moreinfo;

            public RoomInfoDTO(RoomInformation roomInformation) {
                this.minPerson = roomInformation.getMinPerson();
                this.maxPerson = roomInformation.getMaxPerson();
                this.moreinfo = roomInformation.getMoreInfo();
            }
        }

        @Data
        public static class StayOptionDTO{
            private String name;
            private String iconName;

            public StayOptionDTO(Option option) {
                this.name = option.getName();
                this.iconName =option.getIconName();
            }
        }
    }


}

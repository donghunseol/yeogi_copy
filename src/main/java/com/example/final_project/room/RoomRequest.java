package com.example.final_project.room;

import com.example.final_project._core.enums.RoomEnum;
import com.example.final_project.room_information.RoomInformation;
import com.example.final_project.stay.Stay;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RoomRequest {


    // 방등록 DTO
    @Data
    public static class SaveDTO{
        //TODO : 이미지 넣어야함 멀티파트 이후에
        private Integer stayId;
        private String name;
        private String roomNumber;
        private Integer minPerson;
        private String tier;
        private Integer maxPerson;
        private String moreInfo;
        private Integer price;
        private Integer specialPrice;
        private String imagePath;
        private String imageName;
        private LocalTime checkIn;
        private LocalTime checkOut;
        private RoomEnum specialState;
        private LocalDateTime createdAt;

        public Room toEntity(Stay stay){
            return Room.builder()
                    .stay(stay)
                    .name(name)
                    .tier(tier)
                    .imagePath(imagePath)
                    .imageName(imageName)
                    .roomNumber(roomNumber)
                    .price(price)
                    .specialPrice(specialPrice)
                    .specialState(specialState)
                    .createdAt(createdAt)
                    .build();
        }

        public RoomInformation toEntity(Room room){
            return RoomInformation.builder()
                    .minPerson(minPerson)
                    .maxPerson(maxPerson)
                    .moreInfo(moreInfo)
                    .checkIn(checkIn)
                    .checkOut(checkOut)
                    .createdAt(createdAt)
                    .room(room)
                    .build();
        }

    }
}

package com.example.final_project.room;

import com.example.final_project._core.enums.RoomEnum;
import com.example.final_project.room_information.RoomInformation;
import com.example.final_project.stay.Stay;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RoomRequest {


    // 방등록 DTO
    @Data
    public static class SaveDTO{
        private Integer stayId;
        private String name;
        private String roomNumber;
        private Integer minPerson;
        private String tier;
        private Integer maxPerson;
        private String moreInfo;
        private Integer price;
        private Integer specialPrice;
        private LocalTime checkIn;
        private LocalTime checkOut;
        private RoomEnum specialState;
        private MultipartFile imageFile;
        private String announcement;
        private String basicInformation;

        public Room toEntity(Stay stay){
            return Room.builder()
                    .stay(stay)
                    .name(name)
                    .tier(tier)
                    .roomNumber(roomNumber)
                    .price(price)
                    .specialPrice(specialPrice)
                    .specialState(specialState)
                    .build();
        }

        public RoomInformation toEntity(Room room){
            return RoomInformation.builder()
                    .minPerson(minPerson)
                    .maxPerson(maxPerson)
                    .moreInfo(moreInfo)
                    .checkIn(checkIn)
                    .checkOut(checkOut)
                    .announcement(announcement)
                    .basicInformation(basicInformation)
                    .room(room)
                    .build();
        }

    }
}

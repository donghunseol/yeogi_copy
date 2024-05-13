package com.example.final_project.event;

import com.example.final_project._core.enums.EventEnum;
import lombok.Data;

import java.time.LocalDate;

public class EventResponse {


    // 이벤트 디테일 DTO
    @Data
    public static class EventDetailDTO{
        private Integer id;
        private String imageName;
        private String imagePath;
        private String startDate;
        private String endDate;
        private EventEnum state;

        public EventDetailDTO(Event event) {
            this.id = event.getId();
            this.imageName = event.getImageDetailName();
            this.imagePath = event.getImageDetailPath();
            this.startDate = formatDate(event.getStartDate());
            this.endDate = formatDate(event.getEndDate());
            this.state = event.getState();
        }

        private String formatDate(LocalDate dateTime) {
            // 년, 월, 일을 추출하여 문자열로 반환
            return String.format("%04d년 %02d월 %02d일", dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
        }
    }

    //이벤트 리스트 DTO
    @Data
    public static class EventListDTO{
        private Integer id;
        private String imageName;
        private String imagePath;
        private String startDate;
        private String endDate;
        private EventEnum state;

        public EventListDTO(Event event) {
            this.id = event.getId();
            this.imageName = event.getImageName();
            this.imagePath = event.getImagePath();
            this.startDate = formatDate(event.getStartDate());
            this.endDate = formatDate(event.getEndDate());
            this.state = event.getState();
        }

        private String formatDate(LocalDate dateTime) {
            // 년, 월, 일을 추출하여 문자열로 반환
            return String.format("%04d년 %02d월 %02d일", dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
        }
    }
}

package com.example.final_project.scrap;

import com.example.final_project.stay.Stay;
import com.example.final_project.stay_image.StayImage;
import lombok.Data;

public class ScrapResponse {





    // 유저가 스크랩한 숙소 리스트
    @Data
    public static class ScrapListDTO {
        private Integer scrapId; // 스크랩 번호
        private Integer stayId; // 숙소 번호
        private String stayName; // 숙소 이름
        private String stayAddress; // 숙소 주소
        private String stayIntro; // 숙소 소개
        private Integer stayImageId; // 숙소 이미지 번호
        private String stayImageName; // 숙소 이미지 이름
        private String stayImagePath; // 숙소 이미지 경로

        public ScrapListDTO(Scrap scrap, Stay stay, StayImage stayImage) {
            this.scrapId = scrap.getId();
            this.stayId = stay.getId();
            this.stayName = stay.getName();
            this.stayAddress = stay.getAddress();
            this.stayIntro = stay.getIntro();
            this.stayImageId = stayImage.getId();
            this.stayImageName = stayImage.getName();
            this.stayImagePath = stayImage.getPath();
        }
    }
}

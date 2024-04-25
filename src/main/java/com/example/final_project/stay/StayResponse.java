package com.example.final_project.stay;

import com.example.final_project.company.Company;
import lombok.Data;

import java.time.LocalDateTime;

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
        private LocalDateTime createdAt;

        public Save(Stay stay) {
            this.companyId = companyId;
            this.name = name;
            this.category = category;
            this.address = address;
            this.intro = intro;
            this.information = information;
            this.imageName = imageName;
            this.imagePath = imagePath;
            this.createdAt = createdAt;

        }
    }
}
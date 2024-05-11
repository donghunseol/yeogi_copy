package com.example.final_project.scrap;

import com.example.final_project.stay.Stay;
import com.example.final_project.user.User;
import lombok.Data;

import java.time.LocalDateTime;

public class ScrapRequest {

    @Data
    public class ScrapRequestDTO{
        private Integer userId;
        private Integer stayId;
        private LocalDateTime createdAt;


        public Scrap toEntity(Stay stay,User user){
            return Scrap.builder()
                    .stay(stay)
                    .user(user)
                    .createdAt(createdAt)
                    .build();
        }
    }
}

package com.example.final_project.scrap;

import com.example.final_project.stay.Stay;
import com.example.final_project.user.User;
import lombok.Data;

import java.time.LocalDateTime;

public class ScrapResponse {


    //좋아요등록 응답 DTO
    @Data
    public static class Save{
        private Integer id;
        private UserDTO user;
        private StayDTO stay;
        private LocalDateTime createdAt;

        public Save(Scrap scrap, UserDTO user, StayDTO stay) {
            this.id = scrap.getId();
            this.user = user;
            this.stay = stay;
            this.createdAt = scrap.getCreatedAt();
        }

        @Data
        public static class UserDTO{
            private Integer id;
            private String name;

            public UserDTO(User user) {
                this.id = user.getId();
                this.name = user.getName();
            }
        }

        @Data
        public static class StayDTO{
            private Integer id;
            private String businessName;

            public StayDTO(Stay stay) {
                this.id = stay.getId();
                this.businessName = stay.getName();
            }

        }
    }
}

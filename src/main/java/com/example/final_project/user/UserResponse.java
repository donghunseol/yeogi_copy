package com.example.final_project.user;

import lombok.Data;

import java.time.LocalDate;

public class UserResponse {

    // 회원 정보 수정
    @Data
    public static class UpdateDTO {
        private Integer id;
        private String email;
        private String name;
        private String phone;
        private LocalDate birth;

        public UpdateDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.phone = user.getPhone();
            this.birth = user.getBirth();
        }
    }
}

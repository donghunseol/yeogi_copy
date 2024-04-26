package com.example.final_project.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UserRequest {

    @Data
    public static class LoginDTO {
        @NotEmpty
        //@Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email; // 이메일 형태 고정
        @NotEmpty
        //@Size(min = 4, max = 20)
        private String password; // 길이 제한 4~20
    }
}

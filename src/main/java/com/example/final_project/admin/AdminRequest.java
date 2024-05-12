package com.example.final_project.admin;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class AdminRequest {

    // 관리자 로그인
    @Data
    public static class LoginDTO{

        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해주세요")
        private String name;

        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;
    }

}

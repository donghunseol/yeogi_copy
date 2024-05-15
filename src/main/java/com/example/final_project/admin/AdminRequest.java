package com.example.final_project.admin;

import com.example.final_project.faq.Faq;
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


    // 관리자가 기업에게 답글다는 DTO
    @Data
    public static class AdminAnswerDTO{
        private Integer id;
        private String answer;
    }

    //관리자 FAQ 작성 DTO
    @Data
    public static class AdminFaqDTO{
        private Integer userId;
        private String content;
        private String reply;

        public Faq toEntity(Admin admin){
            return Faq.builder()
                    .admin(admin)
                    .content(content)
                    .reply(reply)
                    .build();
        }
    }
}

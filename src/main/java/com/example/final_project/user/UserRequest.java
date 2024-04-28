package com.example.final_project.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

import static com.example.final_project._core.enums.UserEnum.ACTIVE;

public class UserRequest {

    // 로그인
    @Data
    public static class LoginDTO {
        @NotEmpty(message = "이메일을 입력해주세요")
        @Email(message = "이메일 형식으로 작성해주세요")
        //@Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email; // 이메일 형태 고정

        @NotEmpty(message = "비밀번호를 입력해주세요")
        @Size(min = 3, max = 20, message = "비밀번호는 3자 미만이거나 20자를 초과할 수 없습니다")
        //@Size(min = 4, max = 20)
        private String password; // 길이 제한 4~20
    }

    // 회원 가입
    @Data
    public static class JoinDTO {
        @NotEmpty(message = "이메일을 입력해주세요")
        @Email(message = "이메일 형식으로 작성해주세요")
        private String email; // 이메일 (로그인 할 때 아이디로 사용)

        @NotEmpty(message = "비밀번호를 입력해주세요")
        @Size(min = 3, max = 20, message = "비밀번호는 3자 미만이거나 20자를 초과할 수 없습니다")
        private String password; // 비밀번호

        @NotEmpty(message = "이름을 입력해주세요")
        @Size(min = 2, max = 10)
        private String name; // 회원 이름

        @NotEmpty(message = "전화번호를 입력해주세요")
        @Pattern(regexp = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}") // 특정 형태를 가지도록 검증 (정규표현식 사용)
        private String phone; // 전화번호

        @NotEmpty(message = "생년월일을 입력해주세요")
        @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}")
        private LocalDate birth; // 생년월일

        // 직접 입력할 것이니 불필요 하다는 걸 보여주기 위해 남김
        // private UserEnum state; // 상태 (ACTIVE : 회원 유지, QUIT : 회원 탈퇴, BLACK : 신고 받아서 제한된 회원)
        // private Integer reportCount; // 신고 받은 횟수

        public User toEntity() {
            return User.builder()
                    .email(this.email)
                    .password(this.password)
                    .name(this.name)
                    .phone(this.phone)
                    .birth(this.birth)
                    .state(ACTIVE) // USER 는 생성 되었을 때 회원 유지 상태로 생성
                    .reportCount(0) // 처음 생성된 유저는 신고 당하지 않았기에 0 로 생성
                    .build();
        }
    }

    // 회원 정보 수정
    @Data
    public static class UpdateDTO {
        @NotEmpty(message = "이름을 입력해주세요")
        @Size(min = 2, max = 10)
        private String name; // 회원 이름

        @NotEmpty(message = "전화번호를 입력해주세요")
        @Pattern(regexp = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}")
        private String phone; // 전화번호
    }
}

package com.example.final_project.company;

import com.example.final_project._core.enums.CompanyEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class CompanyRequest {

    // 회원가입
    @Data
    public static class JoinDTO {

        private Integer reportCount;
        private CompanyEnum state;

        @NotEmpty(message = "이메일을 입력해주세요")
        @Email(message = "이메일 형식으로 작성해주세요")
        private String email; // 이메일 (로그인 할 때 아이디로 사용)

        @NotEmpty(message = "비밀번호를 입력해주세요")
        @Size(min = 3, max = 20, message = "비밀번호는 3자 이상이거나 20자를 초과할 수 없습니다")
        private String password; // 비밀번호

        @NotEmpty(message = "사업자 이름을 입력해주세요")
        @Size(max = 20, message = "이름은 최대 20자를 초과 할 수 없습니다")
        private String name; // 사업자 이름

        @NotEmpty(message = "등록 상호명을 입력해주세요")
        private String businessName; // 등록 상호명

        @NotEmpty(message = "전화번호를 입력해주세요")
        @Pattern(regexp = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}") // 특정 형태를 가지도록 검증 (정규표현식 사용)
        private String phone; // 전화번호

        @NotEmpty(message = "사업자 등록번호를 입력해주세요")
        @Pattern(regexp = "^(\\d{3})(\\d{2})(\\d{5})$/, `$1-$2-$3`")
        private String businessNumber; // 사업자 등록번호

        @NotEmpty(message = "사업자 주소를 입력해주세요")
        private String businessAddress; // 사업자 주소

        public Company toEntity() {
            return Company.builder()
                    .state(this.state)
                    .reportCount(this.reportCount)
                    .email(this.email)
                    .password(this.password)
                    .phone(this.phone)
                    .name(this.name)
                    .businessAddress(this.businessAddress)
                    .businessName(this.businessName)
                    .businessNumber(this.businessNumber)
                    .build();
        }
    }

    // 로그인
    @Data
    public static class LoginDTO {
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해주세요")
        private String email;

        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;

        public Company toEntity() {
            return Company.builder()
                    .email(this.email)
                    .password(this.password)
                    .build();
        }
    }

    // 업데이트
    @Data
    public static class UpdateDTO {
        private Integer companyId;
        @NotEmpty(message = "비밀번호를 입력해주세요")
        @Size(min = 3, max = 20, message = "비밀번호는 3자 이상이거나 20자를 초과할 수 없습니다")
        private String password;

        @NotEmpty(message = "전화번호를 입력해주세요")
        @Pattern(regexp = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}")
        private String phone;

    }

    // 회원탈퇴
    @Data
    public static class DeleteDTO {
        private Integer companyId;
    }


}

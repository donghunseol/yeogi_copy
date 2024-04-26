package com.example.final_project.company;

import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void findByIdAndPassword_test() {
            // given
            String email = "com1@nate.com";
            String password = "1234";
            // when
            Company sessionUser = companyRepository.findByIdAndPassword(email,password)
                    .orElseThrow(() -> new Exception404("해당 아이디 및 패스워드를 찾을 수 없습니다."));
            // then
            Assertions.assertThat(sessionUser.getEmail()).isEqualTo("com1@nate.com");
        }

    @Test
    public void findByStayId_test() {
            // given
            Integer stayId = 1;
            // when
            Company company = companyRepository.findByStayId(stayId)
                    .orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다."));
            // then
            System.out.println("결과 값============================"+company);
        }
}

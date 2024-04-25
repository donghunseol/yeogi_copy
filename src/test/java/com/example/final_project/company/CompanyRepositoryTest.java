package com.example.final_project.company;

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
            Company sessionUser = companyRepository.findByIdAndPassword(email,password);
            // then
            Assertions.assertThat(sessionUser.getEmail()).isEqualTo("com1@nate.com");
        }
}

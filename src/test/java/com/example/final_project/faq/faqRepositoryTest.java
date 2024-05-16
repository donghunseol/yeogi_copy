package com.example.final_project.faq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class faqRepositoryTest {

    @Autowired
    FaqRepository faqRepository;


    @Test
    public void findAll_test() {
            // given

            // when
            List<Faq> faqList = faqRepository.findAll();
            // eye
            System.out.println(faqList.size());
            // then
        }

    @Test
    public void findAllByExcludeComapny_test() {
        // given

        // when
        List<Faq> faqList = faqRepository.findAllByExcludeComapny();
        // eye
        System.out.println(faqList.size());
        // then
    }
}

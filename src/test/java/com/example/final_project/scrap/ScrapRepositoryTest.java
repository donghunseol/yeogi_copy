package com.example.final_project.scrap;

import com.example.final_project._core.errors.exception.Exception404;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ScrapRepositoryTest {

    @Autowired
    ScrapRepository scrapRepository;


    @Test
    public void findByuserIdWithStayId_test() {
            // given
            Integer userId= 1;
            Integer stayId = 1;
            // when
            Scrap scrap = scrapRepository.findByuserIdWithStayId(userId,stayId).orElseThrow(() -> new Exception404("해당 좋아요를 찾을 수 없습니다"));
            // eye
            System.out.println(scrap.getId());
            // then
            Assertions.assertThat(scrap.getStay().getId().equals(1));
        }
}

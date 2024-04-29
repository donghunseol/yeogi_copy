package com.example.final_project.stay;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class StayRepositoryTest {
    @Autowired
    StayRepository stayRepository;

    @Test
    public void findByCompanyId_test(){
        // given
        Integer companyId = 1;

        // when
        List<Stay> stayList = stayRepository.findByCompanyId(companyId);

        // eye
        System.out.println("findByCompanyIdWithOptionsAndRooms_test size : " + stayList.size());

        // then
        Assertions.assertThat(stayList.getFirst().getName()).isEqualTo("호텔 블루 하버");

    }
}

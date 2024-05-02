package com.example.final_project.pay;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class PayRepositoryTest {

    @Autowired
    PayRepository payRepository;

    @Test
    public void findByRoomId_test(){
        // given
        Integer roomId = 1;
        LocalDate now = LocalDate.of(2023,12,31);

        // when
        Pay pay = payRepository.findByRoomId(roomId,now);

        // eye
        System.out.println("findByRoomId_test pay : " + pay.getWay());
        System.out.println("findByRoomId_test getReservation_id : " + pay.getReservation().getId());

        // then
        Assertions.assertThat(pay.getAmount()).isEqualTo(150000);

    }

}

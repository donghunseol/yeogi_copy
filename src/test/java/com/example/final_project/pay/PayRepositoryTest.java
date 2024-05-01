package com.example.final_project.pay;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;

@DataJpaTest
public class PayRepositoryTest {

    @Autowired
    PayRepository payRepository;

    @Test
    public void findByReservationOfCheckInDate_test(){
        // given
        Integer roomId = 1;
        LocalDate now = LocalDate.of(2023,12,31);

        // when
        Pay pay = payRepository.findByRoomId(roomId,now);

        // eye
        System.out.println("findByRoomId_test pay : " + pay.getWay());

        // then
        Assertions.assertThat(pay.getAmount()).isEqualTo(150000);

    }
}

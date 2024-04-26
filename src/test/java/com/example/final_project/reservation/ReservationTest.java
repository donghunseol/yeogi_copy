package com.example.final_project.reservation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class ReservationTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void findAll_test() {
        // given

        // when
        List<Reservation> reservaiton = reservationRepository.findAll();

        // then
        System.out.println(reservaiton.size());
    }

    @Test
    public void findByUserId_test() {
        // given
        Integer userId = 1;

        // when
        List<Reservation> reservationList = reservationRepository.findByUserId(userId);

        // eye
        System.out.println("findByUserId_test size: " + reservationList.size());

        // then
        Assertions.assertThat(reservationList.getFirst().getCheckInDate()).isEqualTo(LocalDate.of(2023, 12, 31));
    }

}

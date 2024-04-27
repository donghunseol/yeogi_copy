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

}

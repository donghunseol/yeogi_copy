package com.example.final_project.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ReservationTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void findaAll_test() {
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
            // then
            System.out.println("결과========================="+reservationList.size());
        }

    @Test
    public void findByUserIdd_test() {
        // given
        Integer userId = 1;
        // when
        List<Reservation> reservationList = reservationRepository.findByUserIdd(userId);
        // then
        System.out.println("결과========================="+reservationList.size());
        }
}

package com.example.final_project.reservation;

import com.example.final_project._core.errors.exception.Exception404;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void findReservationsByDateRangeAndRoomId_test() {
        // given
        Integer roomId = 2;
        LocalDate startDate = LocalDate.of(2024, 5, 22);
        LocalDate endDate = LocalDate.of(2024, 6, 1);

        // when
        List<Reservation> reservations = reservationRepository.findReservationsByDateRangeAndRoomId(roomId, startDate, endDate)
                .orElseThrow(() -> new Exception404("존재 하지 않는 날짜입니다"));

        // eye
        System.out.println("findReservationsByDateRangeAndRoomId_test : " + reservations.size());
        System.out.println("findReservationsByDateRangeAndRoomId_test : " + reservations.get(0).getCheckInDate());
        System.out.println("findReservationsByDateRangeAndRoomId_test : " + reservations.get(0).getCheckOutDate());
        // System.out.println("findReservationsByDateRangeAndRoomId_test : " + reservations.get(1).getCheckInDate());
        // System.out.println("findReservationsByDateRangeAndRoomId_test : " + reservations.get(1).getCheckOutDate());


        // then

    }
}

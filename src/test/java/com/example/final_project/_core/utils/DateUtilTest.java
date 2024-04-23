package com.example.final_project._core.utils;


import com.example.final_project.reservation.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DateUtilTest {

    @Test
    public void getDateCount_test() {
        // given
        int id = 1;
        LocalDateTime startDateTime = LocalDateTime.of(2024, 4, 28, 12, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 5, 2, 18, 0, 0);

        Reservation reservation = Reservation.builder()
                .id(id)
                .checkIn(startDateTime)
                .checkOut(endDateTime)
                .build();

        // when
        List<LocalDate> allDates = DateUtil.getDate(reservation);

        // eye
        for (LocalDate allDate : allDates) {
            System.out.println("getDateCount_test/date : " + allDate);
        }

        // then

    }
}

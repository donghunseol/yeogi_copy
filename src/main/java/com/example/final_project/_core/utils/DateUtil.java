package com.example.final_project._core.utils;

import com.example.final_project.reservation.Reservation;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {

    // start 일 과 end 일 사이의 모든 날짜 반환
    public static List<LocalDate> getDate(Reservation reservation) {
        // LocalDateTime 에 데이터 넣기
        LocalDateTime startDateTime = reservation.getCheckIn();
        LocalDateTime endDateTime = reservation.getCheckOut();

        // 두 객체 간의 차이를 계산
        Duration duration = Duration.between(startDateTime, endDateTime);

        // 객체의 일수 계산
        Long days = duration.toDays();

        // 객체의 일수 전체 보관
        List<LocalDate> allLocalDate = new ArrayList<>();

        for (int i = 0; i < days; i++) {
            // 날짜만 추출되도록 설정 toLocalDate()
            allLocalDate.add(startDateTime.plusDays(i).toLocalDate());
        }

        return allLocalDate;
    }
}

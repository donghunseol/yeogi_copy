package com.example.final_project._core.utils;

import com.example.final_project.reservation.Reservation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {

    // start 일과 end 일 사이의 모든 날짜 반환
    public static List<LocalDate> getDate(Reservation reservation) {
        // LocalDate 에 데이터 넣기
        LocalDate startDate = reservation.getCheckInDate();
        LocalDate endDate = reservation.getCheckOutDate();

        // 두 객체 간의 차이를 계산
        long days = ChronoUnit.DAYS.between(startDate, endDate);

        // 객체의 일수 전체 보관
        List<LocalDate> allLocalDate = new ArrayList<>();

        for (int i = 0; i < days; i++) {
            // 날짜만 추출되도록 설정 toLocalDate()
            allLocalDate.add(startDate.plusDays(i));
        }

        return allLocalDate;
    }

    // start 일과 end 일 사이의 숫자 반환
    public static Integer getDateCount(Reservation reservation) {
        // LocalDate 에 데이터 넣기
        LocalDate startDate = reservation.getCheckInDate();
        LocalDate endDate = reservation.getCheckOutDate();

        // 두 객체 간의 차이를 계산
        long days = ChronoUnit.DAYS.between(startDate, endDate);

        return Math.toIntExact(days);
    }
}

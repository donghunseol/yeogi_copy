package com.example.final_project._core.utils;


import com.example.final_project._core.enums.RoomEnum;
import com.example.final_project._core.enums.UserEnum;
import com.example.final_project.reservation.Reservation;
import com.example.final_project.room.Room;
import com.example.final_project.user.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

public class DateUtilTest {

    @Test
    public void getDateCount_test() {
        // given
        int id = 1;
        User user = User.builder()
                .email("ssar@nate.com")
                .password("1234")
                .phone("01012341234")
                .state(UserEnum.ACTIVE)
                .birth(LocalDate.of(2000, 01, 01))
                .reportCount(0)
                .build();
        Room room = Room.builder()
                .name("객실 이름")
                .tier("등급")
                .roomNumber("101")
                .price(100000)
                .specialState(RoomEnum.NOT_APPLIED)
                .build();
        LocalDate startDate = LocalDate.of(2024, 4, 28);
        LocalDate endDate = LocalDate.of(2024, 5, 2);

        Reservation reservation = Reservation.builder()
                .user(user)
                .room(room)
                .checkInDate(startDate)
                .checkOutDate(endDate)
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

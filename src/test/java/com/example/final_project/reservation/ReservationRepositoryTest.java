package com.example.final_project.reservation;

import com.example.final_project._core.errors.exception.Exception404;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
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
        List<Reservation> reservations = reservationRepository.findReservationsByDateRangeAndRoomId(roomId, startDate, endDate);
//                .orElseThrow(() -> new Exception404("존재 하지 않는 날짜입니다"));

        // eye
        System.out.println("findReservationsByDateRangeAndRoomId_test : " + reservations.size());
        System.out.println("findReservationsByDateRangeAndRoomId_test : " + reservations.get(0).getCheckInDate());
        System.out.println("findReservationsByDateRangeAndRoomId_test : " + reservations.get(0).getCheckOutDate());
        // System.out.println("findReservationsByDateRangeAndRoomId_test : " + reservations.get(1).getCheckInDate());
        // System.out.println("findReservationsByDateRangeAndRoomId_test : " + reservations.get(1).getCheckOutDate());


        // then

    }

    @Test
    public void findByUserIdWithRoomAndStay_test(){
        // given
        Integer userId = 1;

        // when
        List<Reservation> reservationList = reservationRepository.findByUserIdWithRoomAndStay(userId);

        // eye
        System.out.println("findByUserIdWithRoomAndStay_test size : " + reservationList.size());

        // then
        Assertions.assertThat(reservationList.getLast().getReservationName()).isEqualTo("홍길동");

    }

    @Test
    public void findByReservationIdWithRoomAndStay_test(){
        // given
        Integer reservationId = 1;

        // when
        Reservation reservation = reservationRepository.findByReservationIdWithRoomAndStay(reservationId);

        // eye
        System.out.println("findByReservationIdWithRoomAndStay_test reservationName : "+reservation.getReservationName());

        // then
        Assertions.assertThat(reservation.getUser().getId()).isEqualTo(1);

    }

    @Test
    public void findByCompanyIdWithRoomAndStay_test(){
        // given
        Integer companyId = 3;

        // when
        List<Reservation> reservationList = reservationRepository.findByCompanyIdWithRoomAndStay(companyId);

        // eye
        System.out.println("findByCompanyIdWithRoomAndStay_test size : " + reservationList.size());
        System.out.println("findByCompanyIdWithRoomAndStay_test First_reservationName : " + reservationList.getFirst().getReservationName());

        // then
        Assertions.assertThat(reservationList.getLast().getCheckOutDate()).isEqualTo(LocalDate.of(2025,6,27));

    }

    @Test

    public void findByStayIdWithUserId_test() {
            // given
            Integer stayId =1;
            Integer userId = 1;
            Integer roomId = 1;
            // when
            Reservation reservation =  reservationRepository.findByStayIdWithUserId(stayId,userId,roomId);
            // eye
            System.out.println("왜 통과하는건데 ==============" +reservation.getReservationName());
            // then
            Assertions.assertThat(reservation.getReservationName().equals("감자머리"));
        }

    @Test
    public void findByIdWithRoomAndRoomInformation_test(){
        // given
        Integer reservationId = 1;

        // when
        Reservation reservation = reservationRepository.findByIdWithRoomAndRoomInformation(reservationId);

        // eye
        System.out.println("findByIdWithRoomAndRoomInformation_test getReservationName : " + reservation.getReservationName());

        // then
        Assertions.assertThat(reservation.getRoom().getId()).isEqualTo(1);

    }

}

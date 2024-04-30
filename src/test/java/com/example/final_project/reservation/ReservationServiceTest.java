package com.example.final_project.reservation;

import com.example.final_project.company.SessionCompany;
import com.example.final_project.user.SessionUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReservationServiceTest {
    @Autowired
    ReservationService reservationService;

    @Test
    public void userReservationList_test(){
        // given
        SessionUser sessionUser = SessionUser.builder()
                .id(1)
                .build();

        // when
        List<ReservationResponse.ListDTO> respDTO = reservationService.userReservationList(sessionUser);

        // eye
        System.out.println("reservationList_test size : " + respDTO.size());
        System.out.println("reservationList_test getFirst : " + respDTO.getFirst());

        // then
        Assertions.assertThat(respDTO.getLast().getDate()).isEqualTo(5);
    }

    @Test
    public void reservationDetail_test(){
        // given
        SessionUser sessionUser = SessionUser.builder()
                .id(1)
                .build();
        Integer roomId = 1;

        // when
        ReservationResponse.DetailDTO respDTO = reservationService.reservationDetail(sessionUser, roomId);

        // eye
        System.out.println("reservationDetail_test detail : " + respDTO);

        // then
        Assertions.assertThat(respDTO.getAmount()).isEqualTo(150000);

    }

    @Test
    public void compReservationList_test(){
        // given
        SessionCompany sessionCompany = SessionCompany.builder()
                .id(3)
                .build();

        // when
        List<ReservationResponse.ListDTO> respDTO = reservationService.compReservationList(sessionCompany);

        // eye
        System.out.println("compReservationList_test size : " + respDTO.size());
        System.out.println("compReservationList_test getFirst : " + respDTO.getFirst());

        // then
        Assertions.assertThat(respDTO.getLast().getReservationId()).isEqualTo(13);

    }
}

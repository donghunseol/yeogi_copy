package com.example.final_project.reservation;

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
    public void reservationList_test(){
        // given
        SessionUser sessionUser = SessionUser.builder()
                .id(1)
                .build();

        // when
        List<ReservationResponse.ListDTO> respDTO = reservationService.reservationList(sessionUser);

        // eye
        System.out.println("reservationList_test size : " + respDTO.size());
        System.out.println("reservationList_test getFirst : " + respDTO.getFirst());

        // then
        Assertions.assertThat(respDTO.getLast().getDate()).isEqualTo(5);
    }
}

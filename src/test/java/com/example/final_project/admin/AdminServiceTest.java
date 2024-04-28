package com.example.final_project.admin;

import com.example.final_project.reservation.ReservationResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    AdminService adminService;

    @Test
    public void adminUserList_test(){
        // given


        // when
        List<AdminResponse.userListDTO> userList = adminService.adminUserList();

        // eye
        System.out.println("adminUserList_test size : " + userList.size());
        System.out.println("adminUserList_test userList : " + userList);

        // then
        Assertions.assertThat(userList.getFirst().getEmail()).isEqualTo("ssar@nate.com");

    }

    @Test
    public void adminReservationList_test(){
        // given
        Integer userId = 1;

        // when
        List<ReservationResponse.DetailDTO> reservationList = adminService.adminReservationList(userId);

        // eye
        System.out.println("adminReservationList_test size : " + reservationList.size());

        // then
        Assertions.assertThat(reservationList.getLast().getReservationName()).isEqualTo("홍길동");

    }
}

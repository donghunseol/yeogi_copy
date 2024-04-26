package com.example.final_project.reservation;

import com.example.final_project.room.Room;
import com.example.final_project.user.User;
import lombok.Data;

import java.time.LocalDate;

public class ReservationRequest {

    // 예약하기 DTO
    @Data
    public static class DTO {
        private Integer userId; // 예약한 유저의 번호
        private Integer roomId; // 예약된 방번호
        private LocalDate checkInDate; // 입실 날짜
        private LocalDate checkOutDate; // 퇴실 날짜
        private String reservationName; // 예약자 대표 이름
        private String reservationTel; // 예약자 전화번호

        // 예약한 유저와 예약할 방을 매개변수로 받는다
        public Reservation toEntity(User user, Room room) {
            return Reservation.builder()
                    .user(user)
                    .room(room)
                    .checkInDate(this.checkInDate)
                    .checkOutDate(this.checkOutDate)
                    .reservationName(this.reservationName)
                    .reservationTel(this.reservationTel)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private String reservationName; // 예약자 대표 이름
        private String reservationTel; // 예약자 전화번호

        // 예약자 대표 이름과 예약자 전화 번호만 수정이 가능해서 매개변수를 받지 않는다
        public Reservation toEntity() {
            return Reservation.builder()
                    .reservationName(this.reservationName)
                    .reservationTel(this.reservationTel)
                    .build();
        }
    }
}

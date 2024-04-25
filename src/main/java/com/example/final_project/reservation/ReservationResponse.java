package com.example.final_project.reservation;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationResponse {

    // 에약 하기 후 데이터
    @Data
    public static class DTO {
        private Integer id; // 예약 번호
        private Integer userId; // 예약한 유저 번호
        private Integer roomId; // 예약한 방 번호
        private LocalDate checkInDate; // 입실 시간
        private LocalDate checkOutDate; // 퇴실 시간
        private String reservationName; // 예약한 대표 이름
        private String reservationTel; // 예약한 대표 전화번호
        private LocalDateTime createdAt; // 생성된 날짜

        public DTO(Reservation reservation) {
            this.id = reservation.getId();
            this.userId = reservation.getUser().getId();
            this.roomId = reservation.getRoom().getId();
            this.checkInDate = reservation.getCheckInDate();
            this.checkOutDate = reservation.getCheckOutDate();
            this.reservationName = reservation.getReservationName();
            this.reservationTel = reservation.getReservationTel();
            this.createdAt = reservation.getCreatedAt();
        }
    }
}

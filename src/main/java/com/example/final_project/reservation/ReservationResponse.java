package com.example.final_project.reservation;

import com.example.final_project._core.utils.DateUtil;
import com.example.final_project.pay.Pay;
import com.example.final_project.room.Room;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationResponse {

    // 에약 하기 후 데이터
    // 예약 수정 후 데이터
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

    // 예약 목록 뷰에 필요한 데이터
    @Data
    public static class ListDTO{
        private Integer reservationId; // 예약 번호
        private Integer userId; // 예약한 유저의 번호
        private String stayName; // 예약한 숙소의 이름
        private Integer roomId; // 예약한 객실의 번호
        private String roomName; // 예약한 객실의 이름
        private Integer date; // 숙박하는 날짜 수 (ex.5박)
        private LocalDate checkInDate; // 체크인 날짜
        private LocalTime checkInTime; // 체크인 시간
        private LocalDate checkOutDate; // 체크아웃 날짜
        private LocalTime checkOutTime; // 체크아웃 시간

        public ListDTO(Reservation reservation, Room room) {
            this.reservationId = reservation.getId();
            this.userId = reservation.getUser().getId();
            this.stayName = room.getStay().getName();
            this.roomId = reservation.getRoom().getId();
            this.roomName = room.getName();
            this.date = DateUtil.getDateCount(reservation);
            this.checkInDate = reservation.getCheckInDate();
            this.checkInTime = room.getRoomInformation().getCheckIn();
            this.checkOutDate = reservation.getCheckOutDate();
            this.checkOutTime = room.getRoomInformation().getCheckOut();
        }
    }

    // 예약 상세보기 뷰에 필요한 데이터
    @Data
    public static class DetailDTO{
        private Integer reservationId; // 예약 번호
        private Integer userId; // 예약한 유저의 번호
        private String stayName; // 예약한 숙소의 이름
        private Integer roomId; // 예약한 객실의 번호
        private String roomName; // 예약한 객실의 이름
        private Integer date; // 숙박하는 날짜 수 (ex.5박)
        private LocalDate checkInDate; // 체크인 날짜
        private LocalTime checkInTime; // 체크인 시간
        private LocalDate checkOutDate; // 체크아웃 날짜
        private LocalTime checkOutTime; // 체크아웃 시간
        private String stayAddress; // 숙소 주소
        private String reservationName; // 예약자 대표 이름
        private String reservationTel; // 예약자 대표 연락처
        private LocalDateTime payAt; // 결제 일자
        private Integer amount; // 결제 금액
        private String way; // 결제 수단

        public DetailDTO(Reservation reservation, Room room, Pay pay) {
            this.reservationId = reservation.getId();
            this.userId = reservation.getUser().getId();
            this.stayName = room.getStay().getName();
            this.roomId = reservation.getRoom().getId();
            this.roomName = room.getName();
            this.date = DateUtil.getDateCount(reservation);
            this.checkInDate = reservation.getCheckInDate();
            this.checkInTime = room.getRoomInformation().getCheckIn();
            this.checkOutDate = reservation.getCheckOutDate();
            this.checkOutTime = room.getRoomInformation().getCheckOut();
            this.stayAddress =  room.getStay().getAddress();
            this.reservationName = reservation.getReservationName();
            this.reservationTel = reservation.getReservationTel();
            this.payAt = pay.getCreatedAt();
            this.amount = pay.getAmount();
            this.way = pay.getWay();
        }
    }
}

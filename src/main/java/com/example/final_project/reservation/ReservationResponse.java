package com.example.final_project.reservation;

import com.example.final_project._core.enums.PayEnum;
import com.example.final_project.pay.Pay;
import com.example.final_project.room.Room;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ReservationResponse {

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

    // 에약 하기 후 데이터
    @Data
    public static class SaveDTO {
        private Integer id; // 예약 번호
        private Integer userId; // 예약한 유저 번호
        private Integer roomId; // 예약한 방 번호
        private LocalDate checkInDate; // 입실 시간
        private LocalDate checkOutDate; // 퇴실 시간
        private String reservationName; // 예약한 대표 이름
        private String reservationTel; // 예약한 대표 전화번호
        private LocalDateTime createdAt; // 생성된 날짜

        private List<List<LocalDate>> reservedDates; // 이미 예약된 날짜

        public SaveDTO(Reservation reservation, List<List<LocalDate>> reservedDates) {
            this.id = reservation.getId();
            this.userId = reservation.getUser().getId();
            this.roomId = reservation.getRoom().getId();
            this.checkInDate = reservation.getCheckInDate();
            this.checkOutDate = reservation.getCheckOutDate();
            this.reservationName = reservation.getReservationName();
            this.reservationTel = reservation.getReservationTel();
            this.createdAt = reservation.getCreatedAt();

            if (reservedDates == null) {
                this.reservedDates = null;
            } else {
                this.reservedDates = reservedDates;
            }
        }

        public SaveDTO(List<List<LocalDate>> reservedDates) {
            this.reservedDates = reservedDates;
        }
    }

//    // 로그인한 회원의 예약 내역 페이지 - 목록
//    // 예약 목록 뷰에 필요한 데이터
//    @Data
//    public static class ListDTO{
//        private Integer reservationId; // 예약 번호
//        private Integer userId; // 예약한 유저의 번호
//        private String stayName; // 예약한 숙소의 이름
//        private String stayAddress; // 예약한 숙소의 주소
//        private Integer price; // 예약한 객실의 가격
//        private Integer roomId; // 예약한 객실의 번호
//        private String roomName; // 예약한 객실의 이름
//        private LocalDate checkInDate; // 체크인 날짜
//        private LocalTime checkInTime; // 체크인 시간
//        private LocalDate checkOutDate; // 체크아웃 날짜
//        private LocalTime checkOutTime; // 체크아웃 시간
//
//        public ListDTO(Reservation reservation, Room room) {
//            this.reservationId = reservation.getId();
//            this.userId = reservation.getUser().getId();
//            this.stayName = room.getStay().getName();
//            this.stayAddress = room.getStay().getAddress();
//            this.price = room.getPrice();
//            this.roomId = reservation.getRoom().getId();
//            this.roomName = room.getName();
//            this.checkInDate = reservation.getCheckInDate();
//            this.checkInTime = room.getRoomInformation().getCheckIn();
//            this.checkOutDate = reservation.getCheckOutDate();
//            this.checkOutTime = room.getRoomInformation().getCheckOut();
//        }
//    }

//    // 예약 내역 조회 (상세보기)
//    // 예약 상세보기 뷰에 필요한 데이터
//    @Data
//    public static class DetailDTO{
//        private Integer reservationId; // 예약 번호
//        private Integer userId; // 예약한 유저의 번호
//        private String stayName; // 예약한 숙소의 이름
//        private String stayAddress; // 숙소 주소
//        private LocalDate checkInDate; // 체크인 날짜
//        private LocalTime checkInTime; // 체크인 시간
//        private LocalDate checkOutDate; // 체크아웃 날짜
//        private LocalTime checkOutTime; // 체크아웃 시간
//        private Integer roomId; // 예약한 객실의 번호
//        private String roomName; // 예약한 객실의 이름
//        private String reservationName; // 예약자 대표 이름
//        private String reservationTel; // 예약자 대표 연락처
//
//        // pay 관련 내용은 만약 플러터 쪽에서 안 되면 빼는 걸로
//        private LocalDateTime payAt; // 결제 일자
//        private Integer amount; // 결제 금액
//        private String way; // 결제 수단
//
//        public DetailDTO(Reservation reservation, Room room, Pay pay) {
//            this.reservationId = reservation.getId();
//            this.userId = reservation.getUser().getId();
//            this.stayName = room.getStay().getName();
//            this.stayAddress =  room.getStay().getAddress();
//            this.checkInDate = reservation.getCheckInDate();
//            this.checkInTime = room.getRoomInformation().getCheckIn();
//            this.checkOutDate = reservation.getCheckOutDate();
//            this.checkOutTime = room.getRoomInformation().getCheckOut();
//            this.roomId = reservation.getRoom().getId();
//            this.roomName = room.getName();
//            this.reservationName = reservation.getReservationName();
//            this.reservationTel = reservation.getReservationTel();
//            this.payAt = pay.getCreatedAt();
//            this.amount = pay.getAmount();
//            this.way = pay.getWay();
//        }
//    }

    // 예약 내역 조회 (상세보기)
    // 예약 상세보기 뷰에 필요한 데이터
    @Data
    public static class ReservationDTO {
        private Integer reservationId; // 예약 번호
        private Integer userId; // 예약한 유저의 번호
        private String stayName; // 예약한 숙소의 이름
        private String stayAddress; // 숙소 주소
        private LocalDate checkInDate; // 체크인 날짜
        private LocalTime checkInTime; // 체크인 시간
        private LocalDate checkOutDate; // 체크아웃 날짜
        private LocalTime checkOutTime; // 체크아웃 시간
        private Integer roomId; // 예약한 객실의 번호
        private String roomName; // 예약한 객실의 이름
        private String roomImageName; // 예약한 객실의 이미지 이름
        private String roomImagePath; // 예약한 객실의 이미지 경로
        private String reservationName; // 예약자 대표 이름
        private String reservationTel; // 예약자 대표 연락처
        private Integer reviewId;
        // pay 관련 내용은 만약 플러터 쪽에서 안 되면 빼는 걸로
        private Integer payId; // 결제 번호
        private String payState; // 결제 상태
        private LocalDateTime payAt; // 결제 일자
        private Integer amount; // 결제 금액
        private String way; // 결제 수단

        public ReservationDTO(Reservation reservation, Room room, Pay pay) {
            this.reservationId = reservation.getId();
            this.userId = reservation.getUser().getId();
            this.stayName = room.getStay().getName();
            this.stayAddress = room.getStay().getAddress();
            this.checkInDate = reservation.getCheckInDate();
            this.checkInTime = room.getRoomInformation().getCheckIn();
            this.checkOutDate = reservation.getCheckOutDate();
            this.checkOutTime = room.getRoomInformation().getCheckOut();
            this.roomId = reservation.getRoom().getId();
            this.roomName = room.getName();
            this.roomImageName = room.getImageName();
            this.roomImagePath = room.getImagePath();
            this.reservationName = reservation.getReservationName();
            this.reservationTel = reservation.getReservationTel();
            this.reviewId =reservation.getReviewid();
            this.payId = pay.getId();
            this.payAt = pay.getCreatedAt();
            this.amount = pay.getAmount();
            if (pay.getState().equals(PayEnum.COMPLETION)) {
                this.payState = "결제 완료";
            } else if (pay.getState().equals(PayEnum.PROCESSING)) {
                this.payState = "결제 진행 중";
            } else if (pay.getState().equals(PayEnum.REFUND)) {
                this.payState = "환불 처리";
            }
            switch (pay.getWay()) {
                case "Credit Card" -> this.way = "카드 결제";
                case "Kakao Pay" -> this.way = "카카오페이";
                case "On Site Payment" -> this.way = "현장 결제";
                case null, default -> this.way = pay.getWay(); // TODO: 나머지는 추후에 상태값을 한글로 바꾸기
            }
        }
    }
}

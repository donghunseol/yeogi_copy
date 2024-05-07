package com.example.final_project.company;

import com.example.final_project._core.enums.PayEnum;
import com.example.final_project._core.enums.RoomEnum;
import com.example.final_project._core.utils.DateUtil;
import com.example.final_project.pay.Pay;
import com.example.final_project.reservation.Reservation;
import com.example.final_project.room.Room;
import com.example.final_project.stay.Stay;
import com.example.final_project.stay_image.StayImage;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompanyResponse {

   // [숙소 관리] 로그인한 기업이 등록한 숙소 정보
   @Data
   public static class CompanyStayListDTO {
      private Integer stayId; // 숙소 번호
      private String stayName; // 숙소 이름
      private Integer imageId; // 숙소 이미지 번호
      private String imagePath; // 이미지 경로
      private String stayAddress; // 숙소 주소
      private String stayCategory; // 숙소 분류 (ex.호텔)

      public CompanyStayListDTO(Stay stay, StayImage stayImage){
         this.stayId = stay.getId();
         this.stayName = stay.getName();
         this.imageId = stayImage.getId();
         this.imagePath = stayImage.getPath();
         this.stayAddress = stay.getAddress();
         this.stayCategory = stay.getCategory();
      }
   }

   // [숙소 관리 - 숙소 상세보기] 로그인한 기업이 등록한 특정 숙소 상세보기
   @Data
   public static class CompanyStayDetailDTO {
      private Integer stayId; // 숙소 번호
      private Integer roomId; // 객실 번호
      private String roomImagePath; // 객실 이미지 경로
      private String roomTier; // 객실 등급
      private Long tierCount; // 티어 갯수

      public CompanyStayDetailDTO(Room room, Long tierCount){
         this.stayId = room.getStay().getId();
         this.roomId = room.getId();
         this.roomImagePath = room.getImagePath();
         this.roomTier = room.getTier();
         this.tierCount = tierCount;
      }
   }

   // [숙소 관리 - 숙소 상세보기 - 객실 상세보기] 로그인한 기업이 등록한 특정 숙소의 객실 상세보기
   @Data
   public static class CompanyRoomDetailDTO{
      private Integer roomId; // 객실 번호
      private Integer reservationId; // 예약 번호
      private String roomImagePath; // 객실 이미지 경로
      private String roomNumber; // 호실
      private String isReservation; // 예약 가능 여부
      private String checkOutDate; // 체크아웃 날짜
      private PayEnum payState; // 결제 상태


      public CompanyRoomDetailDTO(Room room, Pay pay) { // resevation을 조회해서 넣으면 예약여부를 체크할 수 없어서 pay에서 getReservation해서 확인
         this.roomId = room.getId();
         this.roomImagePath = room.getImagePath();
         this.roomNumber = room.getRoomNumber();
         if (pay != null && pay.getReservation() != null) {
            this.reservationId = pay.getReservation().getId();
            this.isReservation = "예약 완료";
            this.checkOutDate = pay.getReservation().getCheckOutDate().toString();
            this.payState = pay.getState();
         } else {
            this.reservationId = null;
            this.isReservation = "예약 가능";
            this.checkOutDate = "";
            this.payState = null;
         }
      }
   }

   // [숙소 관리 - 숙소 상세보기 - 객실 상세보기] 로그인한 기업이 등록한 숙소 정보 + 객실의 티어
   @Data
   public static class CompanyStayListAndTierDTO {
      private Integer stayId; // 숙소 번호
      private String stayName; // 숙소 이름
      private Integer imageId; // 숙소 이미지 번호
      private String imagePath; // 이미지 경로
      private String stayAddress; // 숙소 주소
      private String stayCategory; // 숙소 분류 (ex.호텔)
      private String roomTier; // 객실의 티어

      public CompanyStayListAndTierDTO(Stay stay, StayImage stayImage, String tier){
         this.stayId = stay.getId();
         this.stayName = stay.getName();
         this.imageId = stayImage.getId();
         this.imagePath = stayImage.getPath();
         this.stayAddress = stay.getAddress();
         this.stayCategory = stay.getCategory();
         this.roomTier = tier;
      }
   }

   // [숙소 관리 - 숙소 상세보기 - 객실 상세보기] 로그인한 기업이 등록한 객실의 예약 상세보기
   @Data
   public static class CompanyReservationDetailDTO {
      private Integer reservationId; // 예약 아이디
      private Integer roomId; // 객실 아이디
      private String roomImagePath; // 객실 이미지 경로
      private String roomName; // 객실 이름(호실)
      private String reservationName; // 예약자 이름
      private String reservationTel; // 예약자 전화번호
      private LocalDate checkInDate; // 체크인 날짜
      private LocalTime checkInTime; // 체크인 시간
      private LocalDate checkOutDate; // 체크아웃 날짜
      private LocalTime checkOutTime; // 체크아웃 시간
      private String isDiscount; // 특가 적용
      private Integer price; // 결제 금액

      public CompanyReservationDetailDTO(Reservation reservation){
         this.reservationId = reservation.getId();
         this.roomId = reservation.getRoom().getId();
         this.roomImagePath = reservation.getRoom().getImagePath();
         this.roomName = reservation.getRoom().getName();
         this.reservationName = reservation.getReservationName();
         this.reservationTel = reservation.getReservationTel();
         this.checkInDate = reservation.getCheckInDate();
         this.checkInTime = reservation.getRoom().getRoomInformation().getCheckIn();
         this.checkOutDate = reservation.getCheckOutDate();
         this.checkOutTime = reservation.getRoom().getRoomInformation().getCheckOut();
         if(reservation.getRoom().getSpecialState() == RoomEnum.APPLIED){
            this.isDiscount = "적용됨";
            this.price = reservation.getRoom().getSpecialPrice();
         }else{
            this.isDiscount = "적용 안 됨";
            this.price = reservation.getRoom().getPrice();
         }
      }
   }

   // 로그인한 기업이 등록한 숙소의 예약 내역 페이지 - 목록
   // 예약 목록 뷰에 필요한 데이터
   @Data
   public static class ReservationListDTO{
      private Integer reservationId; // 예약 번호
      private Integer userId; // 예약한 유저의 번호
      private String stayName; // 예약한 숙소의 이름
      private String stayAddress; // 예약한 숙소의 주소
      private Integer price; // 예약한 객실의 가격
      private Integer roomId; // 예약한 객실의 번호
      private String roomName; // 예약한 객실의 이름
      private Integer date; // 숙박하는 날짜 수 (ex.5박)
      private LocalDate checkInDate; // 체크인 날짜
      private LocalTime checkInTime; // 체크인 시간
      private LocalDate checkOutDate; // 체크아웃 날짜
      private LocalTime checkOutTime; // 체크아웃 시간

      public ReservationListDTO(Reservation reservation, Room room) {
         this.reservationId = reservation.getId();
         this.userId = reservation.getUser().getId();
         this.stayName = room.getStay().getName();
         this.stayAddress = room.getStay().getAddress();
         this.price = room.getPrice();
         this.roomId = reservation.getRoom().getId();
         this.roomName = room.getName();
         this.date = DateUtil.getDateCount(reservation);
         this.checkInDate = reservation.getCheckInDate();
         this.checkInTime = room.getRoomInformation().getCheckIn();
         this.checkOutDate = reservation.getCheckOutDate();
         this.checkOutTime = room.getRoomInformation().getCheckOut();
      }
   }
}
package com.example.final_project.company;

import com.example.final_project._core.enums.PayEnum;
import com.example.final_project.pay.Pay;
import com.example.final_project.room.Room;
import com.example.final_project.stay.Stay;
import com.example.final_project.stay_image.StayImage;
import lombok.Data;

public class CompanyResponse {

   // [숙소 관리] 로그인한 기업이 등록한 숙소 정보
   @Data
   public static class companyStayListDTO {
      private Integer stayId; // 숙소 번호
      private String stayName; // 숙소 이름
      private Integer imageId; // 숙소 이미지 번호
      private String imagePath; // 이미지 경로
      private String stayAddress; // 숙소 주소
      private String stayCategory; // 숙소 분류 (ex.호텔)

      public companyStayListDTO(Stay stay, StayImage stayImage){
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
   public static class companyStayDetailDTO {
      private Integer stayId; // 숙소 번호
      private Integer roomId; // 객실 번호
      private String roomImagePath; // 객실 이미지 경로
      private String roomTier; // 객실 등급
      private Long tierCount; // 티어 갯수

      public companyStayDetailDTO(Room room, Long tierCount){
         this.stayId = room.getStay().getId();
         this.roomId = room.getId();
         this.roomImagePath = room.getImagePath();
         this.roomTier = room.getTier();
         this.tierCount = tierCount;
      }
   }

   // [숙소 관리 - 숙소 상세보기 - 객실 상세보기] 로그인한 기업이 등록한 특정 숙소의 객실 상세보기
   @Data
   public static class companyRoomDetailDTO{
      private Integer roomId; // 객실 번호
      private String roomImagePath; // 객실 이미지 경로
      private String roomNumber; // 호실
      private String isReservation; // 예약 가능 여부
      private String checkOutDate; // 체크아웃 날짜
      private PayEnum payState; // 결제 상태


      public companyRoomDetailDTO(Room room, Pay pay) { // resevation을 조회해서 넣으면 예약여부를 체크할 수 없어서 pay에서 getReservation해서 확인
         this.roomId = room.getId();
         this.roomImagePath = room.getImagePath();
         this.roomNumber = room.getRoomNumber();
         if(pay.getReservation().getId()!=null){
            this.isReservation = "예약 완료";
         }else{
            this.isReservation = "예약 가능";
         }
         if(pay.getReservation().getId()!=null){
            this.checkOutDate = pay.getReservation().getCheckOutDate().toString();
         }else{
            this.checkOutDate = "";
         }
         this.payState = pay.getState();
      }
   }
}

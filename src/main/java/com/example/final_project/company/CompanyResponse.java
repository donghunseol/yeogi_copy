package com.example.final_project.company;

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
      private String roomImagePath; // 객실 이미지 경로
      private String roomName; // 객실 이름(=객실 유형)
      private Integer minPerson; // 최소 인원
      private Integer maxPerson; // 최대 인원
      private String roomTier; // 객실 등급

      public companyStayDetailDTO(Room room){
         this.roomImagePath = room.getImagePath();
         this.roomName = room.getName();
         this.minPerson = room.getRoomInformation().getMinPerson();
         this.maxPerson = room.getRoomInformation().getMaxPerson();
         this.roomTier = room.getTier();
      }
   }
}

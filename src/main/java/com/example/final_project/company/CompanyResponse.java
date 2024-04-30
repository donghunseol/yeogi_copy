package com.example.final_project.company;

import com.example.final_project.stay.Stay;
import com.example.final_project.stay_image.StayImage;
import lombok.Data;

import java.time.LocalDateTime;

public class CompanyResponse {

   // [숙소 관리] 로그인한 기업이 등록한 숙소 정보
   @Data
   public static class companyStayListDTO {
      private Integer stayId; // 숙소 번호
      private String stayName; // 숙소 이름
      private Integer imageId; // 숙소 이미지 번호
      private String imagePath; // 이미지 경로
      private String stayAddress; // 숙소 주소
      private String stayCategory; // 숙소 분류

      public companyStayListDTO(Stay stay, StayImage stayImage){
         this.stayId = stay.getId();
         this.stayName = stay.getName();
         this.imageId = stayImage.getId();
         this.imagePath = stayImage.getPath();
         this.stayAddress = stay.getAddress();
         this.stayCategory = stay.getCategory();
      }
   }
}

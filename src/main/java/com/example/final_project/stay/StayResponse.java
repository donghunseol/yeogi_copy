package com.example.final_project.stay;

import com.example.final_project._core.enums.EventEnum;
import com.example.final_project._core.enums.ReviewEnum;
import com.example.final_project._core.enums.RoomEnum;
import com.example.final_project._core.enums.StayEnum;
import com.example.final_project.option.Option;
import com.example.final_project.room.Room;
import com.example.final_project.review.Review;
import com.example.final_project.room_information.RoomInformation;

import com.example.final_project.stay_image.StayImage;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class StayResponse {

    // 숙소 검색 응답 DTO
    @Data
    public static class SearchListDTO {
        private Integer id; // 숙소 번호
        private String name; // 숙소 이름
        private String address; // 숙소 주소
        private String intro; // 숙소 소개

        public SearchListDTO(Stay stay) {
            this.id = stay.getId();
            this.name = stay.getName();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
        }
    }

    // 숙소 등록 응답DTO
    @Data
    public static class Save {
        private Integer companyId;
        private String name;
        private String category;
        private String address;
        private String intro;
        private String information;
        private List<OptionDTO> optionList;
        private LocalDateTime createdAt;

        @Builder
        public Save(Stay stay, List<Option> optionList) {
            this.companyId = stay.getCompany().getId();
            this.name = stay.getName();
            this.category = stay.getCategory();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
            this.information = stay.getInformation();
            this.createdAt = stay.getCreatedAt();
            this.optionList = optionList.stream()
                    .map(OptionDTO::new)
                    .collect(Collectors.toList());
        }

        @Data
        public static class OptionDTO {
            private String name;
            private String iconName;

            public OptionDTO(Option option) {
                this.name = option.getName();
                this.iconName = option.getIconName();
            }
        }
    }

    //숙소 수정 폼 DTO
    @Data
    public static class UpdateFormDTO{
        private Integer id;
        private String name;
        private String category;
        private List<OptionChekedDTO> options;
        private String address;
        private String intro;
        private String information;

        public UpdateFormDTO(Stay stay, List<OptionChekedDTO> options) {
            this.id = stay.getId();
            this.name = stay.getName();
            this.category = stay.getCategory();
            this.options = options;
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
            this.information = stay.getInformation();
        }

        @Data
        public static class OptionChekedDTO{
            private boolean 피트니스 = false;
            private boolean 침대 = false;
            private boolean 미니바 = false;
            private boolean 수영장 = false;
            private boolean 오션뷰 = false;
            private boolean 와이파이 = false;

            public OptionChekedDTO(List<Option> optionNames) {
                for (Option option : optionNames){
                    if (option.getName().equals("피트니스")){
                        this.피트니스 = true;
                    } else if (option.getName().equals("침대")) {
                        this.침대 = true;
                    } else if (option.getName().equals("미니바")) {
                        this.미니바 = true;
                    } else if (option.getName().equals("수영장")) {
                        this.수영장 = true;
                    } else if (option.getName().equals("오션뷰")) {
                        this.오션뷰 = true;
                    } else if (option.getName().equals("와이파이")) {
                        this.와이파이 = true;
                    }

                }

            }
        }
    }


    // 숙소수정Form 응답DTO
    @Data
    public static class UpdateForm {
        private Integer stayId;
        private String intro;
        private String information;
        private List<OptionDTO> optionList;
        private LocalDateTime createdAt;

        public UpdateForm(Stay stay, List<Option> optionList) {
            this.stayId = stay.getId();
            this.intro = stay.getIntro();
            this.information = stay.getInformation();
            this.createdAt = stay.getCreatedAt();
            this.optionList = optionList.stream()
                    .map(OptionDTO::new)
                    .collect(Collectors.toList());
        }

        @Data
        public static class OptionDTO {
            private String name;
            private String iconName;

            public OptionDTO(Option option) {
                this.name = option.getName();
                this.iconName = option.getIconName();
            }
        }
    }

    // 숙소수정 응답DTO
    @Data
    public static class Update {
        private Integer stayId;
        private String intro;
        private String information;
        private List<OptionDTO> optionList;
        private LocalDateTime createdAt;

        public Update(Stay stay) {
            this.stayId = stay.getId();
            this.intro = stay.getIntro();
            this.information = stay.getInformation();
            this.optionList = stay.getOptions().stream().map(OptionDTO::new).toList();
            this.createdAt = stay.getCreatedAt();
        }

        @Data
        public static class OptionDTO {
            private String name;
            private String iconName;

            public OptionDTO(Option option) {
                this.name = option.getName();
                this.iconName = option.getIconName();
            }

        }
    }

    // 숙소삭제 응답DTO
    @Data
    public static class Delete {
        private StayEnum state;

        public Delete(Stay stay) {
            this.state = stay.getState();
        }
    }

    // [해외] 숙소 리스트
    @Data
    public static class OverseaList{
        private Integer stayId;
        private String imageName;
        private String imagePath;
        private String name;
        private String address;
        private String intro;

        public OverseaList(Stay stay, StayImage stayImage) {
            this.stayId = stay.getId();
            this.imageName = stayImage.getName();
            this.imagePath = stayImage.getPath();
            this.name = stay.getName();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
        }
    }

    // [특가] 숙소 리스트
    @Data
    public static class SaleList{
        private Integer stayId;
        private String imageName;
        private String imagePath;
        private String name;
        private String address;
        private String intro;

        public SaleList(Stay stay, StayImage stayImage) {
            this.stayId = stay.getId();
            this.imageName = stayImage.getName();
            this.imagePath = stayImage.getPath();
            this.name = stay.getName();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
        }
    }

    // [호텔] 숙소 리스트
    @Data
    public static class HotelList{
        private Integer stayId;
        private String imageName;
        private String imagePath;
        private String name;
        private String address;
        private String intro;

        public HotelList(Stay stay, StayImage stayImage) {
            this.stayId = stay.getId();
            this.imageName = stayImage.getName();
            this.imagePath = stayImage.getPath();
            this.name = stay.getName();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
        }
    }

    // [캠핑] 숙소 리스트
    @Data
    public static class CampingList{
        private Integer stayId;
        private String imageName;
        private String imagePath;
        private String name;
        private String address;
        private String intro;

        public CampingList(Stay stay, StayImage stayImage) {
            this.stayId = stay.getId();
            this.imageName = stayImage.getName();
            this.imagePath = stayImage.getPath();
            this.name = stay.getName();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
        }
    }

    // [모텔] 숙소 리스트
    @Data
    public static class MotelList{
        private Integer stayId;
        private String imageName;
        private String imagePath;
        private String name;
        private String address;
        private String intro;

        public MotelList(Stay stay, StayImage stayImage) {
            this.stayId = stay.getId();
            this.imageName = stayImage.getName();
            this.imagePath = stayImage.getPath();
            this.name = stay.getName();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
        }
    }

    // [펜션] 숙소 리스트
    @Data
    public static class PensionList{
        private Integer stayId;
        private String imageName;
        private String imagePath;
        private String name;
        private String address;
        private String intro;

        public PensionList(Stay stay, StayImage stayImage) {
            this.stayId = stay.getId();
            this.imageName = stayImage.getName();
            this.imagePath = stayImage.getPath();
            this.name = stay.getName();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
        }
    }

    // [홈 & 빌라] 숙소 리스트
    @Data
    public static class HomeAndVillaList{
        private Integer stayId;
        private String imageName;
        private String imagePath;
        private String name;
        private String address;
        private String intro;

        public HomeAndVillaList(Stay stay, StayImage stayImage) {
            this.stayId = stay.getId();
            this.imageName = stayImage.getName();
            this.imagePath = stayImage.getPath();
            this.name = stay.getName();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
        }
    }

    // [게하] 숙소 리스트
    @Data
    public static class GuesthouseList{
        private Integer stayId;
        private String imageName;
        private String imagePath;
        private String name;
        private String address;
        private String intro;

        public GuesthouseList(Stay stay, StayImage stayImage) {
            this.stayId = stay.getId();
            this.imageName = stayImage.getName();
            this.imagePath = stayImage.getPath();
            this.name = stay.getName();
            this.address = stay.getAddress();
            this.intro = stay.getIntro();
        }
    }

    // [홈]숙소 카테고리별 리스트
    @Data
    public static class AllList{
        private List<SpecialPriceDTO> specialprices;
        private List<DomesticDTO> domestics;
        private List<OverseaDTO> overseas;

        public AllList(List<SpecialPriceDTO> specialprices, List<DomesticDTO> domestics, List<OverseaDTO> overseas) {
            this.specialprices = specialprices;
            this.domestics = domestics;
            this.overseas = overseas;
        }

        @Data
        public static class SpecialPriceDTO{
            private String imageName;
            private String imagePath;
            private String name;

            public SpecialPriceDTO(Stay stay, StayImage stayImage) {
                this.imageName = stayImage.getName();
                this.imagePath = stayImage.getPath();
                this.name = stay.getName();
            }
        }

        @Data
        public static class DomesticDTO{
            private String imageName;
            private String imagePath;
            private String name;

            public DomesticDTO(Stay stay, String imageName, String imagePath) {
                this.imageName = imageName;
                this.imagePath = imagePath;
                this.name = stay.getName();
            }
        }

        @Data
        public static class OverseaDTO{
           private String imageName;
           private String imagePath;
           private String name;

            public OverseaDTO(Stay stay, StayImage stayImage) {
                this.imageName = stayImage.getName();
                this.imagePath = stayImage.getPath();
                this.name = stay.getName();
            }
        }
    }

    @Data
    public static class StayDetail{
        private StayDetail.StayContentsDTO stayContents;
        private List<StayDetail.RoomContentsDTO> roomContents;

        public StayDetail(StayDetail.StayContentsDTO stayContents, List<StayDetail.RoomContentsDTO> roomContents) {
            this.stayContents = stayContents;
            this.roomContents = roomContents;
        }

        // section1 (숙소 이름, 찜 여부, 숙소 이미지, 숙소 리뷰, 숙소 편의시설)
        @Data
        public static class StayContentsDTO{
            private StayDTO stay;
            // TODO: 찜 필드 추가
            private List<StayImageDTO> stayImageList;
            private List<ReviewDTO> reviewList;
            private List<OptionDTO> optionList;

            public StayContentsDTO(StayDTO stay, List<StayImageDTO> stayImageList, List<ReviewDTO> reviewList, List<OptionDTO> optionList){
                this.stay = stay;
                this.stayImageList = stayImageList;
                this.reviewList = reviewList;
                this.optionList = optionList;
            }

            @Data
            public static class StayDTO {
                private Integer stayId; // 숙소 번호
                private String stayName; // 숙소 이름

                public StayDTO(Stay stay) {
                    this.stayId = stay.getId();
                    this.stayName = stay.getName();
                }
            }

            @Data
            public static class StayImageDTO {
                private Integer stayImageId; // 숙소 이미지 번호
                private String stayImagePath; // 숙소 이미지 경로

                public StayImageDTO(StayImage stayImage) {
                    this.stayImageId = stayImage.getId();
                    this.stayImagePath = stayImage.getPath();
                }
            }

            @Data
            public static class ReviewDTO {
                private Integer reviewId; // 리뷰 번호
                private Integer userId; // 회원 번호
                private String userName; // 회원 이름
                private Integer stayId; // 리뷰한 숙소의 번호
                private Integer reviewScore; // 리뷰의 평점
                private String reviewContent; // 리뷰의 내용
                private ReviewEnum isDelete; // 리뷰 삭제 여부
                private Integer reviewParentId; // 리뷰의 부모 댓글
                private List<ReviewDTO> reviewChildrenList; // 리뷰의 자식 댓글(대댓글)

                public ReviewDTO(Review review) {
                    this.reviewId = review.getId();
                    this.userId = review.getUser().getId();
                    this.userName = review.getUser().getName();
                    this.stayId = review.getStay().getId();
                    this.reviewScore = review.getScore();
                    this.reviewContent = review.getContent();
                    this.isDelete = review.getIsDelete();
                    if (review.getParent() != null) {
                        this.reviewParentId = review.getParent().getId();
                    }
                    this.reviewChildrenList = review.getChildren().stream().map(ReviewDTO::new).collect(Collectors.toList());
                }
            }

            @Data
            public static class OptionDTO {
                private Integer optionId;
                private String name;
                private String iconName;

                public OptionDTO(Option option){
                    this.optionId = option.getId();
                    this.name = option.getName();
                    this.iconName = option.getIconName();
                }
            }

        }

        // section2 (객실 리스트)
        @Data
        public static class RoomContentsDTO{
            private Integer roomId; // 객실 번호
            private String roomName; // 객실 이름
            private String roomTier; // 객실 티어
            private Integer roomPrice; // 객실 가격
            private RoomEnum roomSpecialState; // 객실 특가 적용 여부
            private Integer roomSpecialPrice; // 객실 특가
            private String roomImagePath; // 객실 대표 이미지
            private LocalTime checkInTime; // 객실 체크인 시간
            private LocalTime checkOutTime; // 객실 체크아웃 시간

            public RoomContentsDTO(Room room, RoomInformation roomInformation){
                this.roomId = room.getId();
                this.roomName = room.getName();
                this.roomTier = room.getTier();
                this.roomPrice = room.getPrice();
                this.roomSpecialState = room.getSpecialState();
                this.roomSpecialPrice = room.getSpecialPrice();
                this.roomImagePath = room.getImagePath();
                this.checkInTime = roomInformation.getCheckIn();
                this.checkOutTime = roomInformation.getCheckOut();
            }
        }
    }
}
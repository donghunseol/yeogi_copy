package com.example.final_project.admin;

import com.example.final_project._core.enums.CompanyEnum;
import com.example.final_project._core.enums.StayEnum;
import com.example.final_project._core.enums.UserEnum;
import com.example.final_project.company.Company;
import com.example.final_project.option.Option;
import com.example.final_project.pay.Pay;
import com.example.final_project.reservation.Reservation;
import com.example.final_project.review.Review;
import com.example.final_project.review.ReviewResponse;
import com.example.final_project.room.Room;
import com.example.final_project.stay.Stay;
import com.example.final_project.user.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AdminResponse {

    // 관리자 페이지에서 출력할 유저 정보
    @Data
    public static class userListDTO {
        private Integer userId; // 유저 번호
        private String name; // 회원 이름
        private String email; // 이메일
        private Integer reportCount; // 신고 받은 횟수
        private UserEnum state; // 상태 (ACTIVE : 회원 유지, QUIT : 회원 탈퇴, BLACK : 신고 받아서 제한된 회원)

        public userListDTO(User user) {
            this.userId = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.reportCount = user.getReportCount();
            this.state = user.getState();
        }
    }

    // 관리자 페이지에서 출력할 유저 상세 정보
    @Data
    public static class userDetailDTO {
        private Integer id; // 유저 번호
        private String email; // 이메일 (로그인 할 때 아이디로 사용)
        private String name; // 회원 이름
        private String phone; // 전화번호
        private UserEnum state; // 상태 (ACTIVE : 회원 유지, QUIT : 회원 탈퇴, BLACK : 신고 받아서 제한된 회원)
        private LocalDate birth; // 생년월일
        private Integer reportCount; // 신고 받은 횟수
        private LocalDateTime createdAt; // 유저 가입 일자

        public userDetailDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.phone = user.getPhone();
            this.state = user.getState();
            this.birth = user.getBirth();
            this.reportCount = user.getReportCount();
            this.createdAt = user.getCreatedAt();
        }
    }


    // 관리자 페이지에서 출력할 기업 정보
    @Data
    public static class companyListDTO {
        private Integer id; // 기업 번호
        private String businessName; // 등록 상호명
        private String phone; // 사업자 전화번호
        private String name; // 사업자 이름
        private String stateMessage; // 상태 메시지 한글
        private String stateColor; // 상태 메시지 색
        Boolean isBlack; // true 면 블랙 등록 / false 면 블랙 미등록
        Boolean isBlackCancel; // true 면 블랙 취소 버튼 활성 / false 면 블랙 취소 버튼 비활성

        public companyListDTO(Company company) {
            this.id = company.getId();
            this.businessName = company.getBusinessName();
            this.phone = company.getPhone();
            this.name = company.getName();
            isBlack = false;
            isBlackCancel = true;
            if (company.getState() == CompanyEnum.ACTIVE) {
                this.stateMessage = "승인";
                this.stateColor = "approval";
            } else if (company.getState() == CompanyEnum.PROGRESSING) {
                this.stateMessage = "대기중";
                this.stateColor = "wait";
            } else if (company.getState() == CompanyEnum.QUIT) {
                this.stateMessage = "탈퇴";
                this.stateColor = "drop-out";
            } else if (company.getState() == CompanyEnum.REJECT) {
                this.stateMessage = "거절";
                this.stateColor = "refuse";
            } else if (company.getState() == CompanyEnum.BLACK) {
                this.stateMessage = "블랙";
                this.stateColor = "black-list";
                this.isBlack = true;
                this.isBlackCancel = false;
            }
        }
    }

    // 관리자 페이지에서 출력할 특정 기업의 숙소 정보
    @Data
    public static class companyStayDetailDTO {
        private Integer id; // 숙소 번호
        private Company company; // 해당 숙소의 기업
        private String name; // 숙소 이름
        private String category; // 숙소 분류
        private String address; // 숙소 주소
        private StayEnum state; // 상태 (TRUE : 사용 / FALSE : 탈퇴)
        private String intro; // 숙소 소개
        private String information; // 숙소 이용 정보
        private List<Option> options = new ArrayList<>(); // 옵션 리스트
        private List<Room> rooms = new ArrayList<>(); // 객실 리스트
        private LocalDateTime createdAt; // 숙소 등록 일자

        public companyStayDetailDTO(Stay stay, List<Room> rooms) {
            this.id = stay.getId();
            this.company = stay.getCompany();
            this.name = stay.getName();
            this.category = stay.getCategory();
            this.address = stay.getAddress();
            this.state = stay.getState();
            this.intro = stay.getIntro();
            this.information = stay.getInformation();
            this.options = stay.getOptions();
            this.rooms = rooms;
            this.createdAt = stay.getCreatedAt();
        }
    }

    // 관리자 페이지에서 특정 회원의 예약 리스트 뷰에 필요한 데이터
    @Data
    public static class userReservationDTO{
        private Integer reservationId; // 예약 번호
        private Integer userId; // 예약한 유저의 번호
        private String stayName; // 예약한 숙소의 이름
        private Integer roomId; // 예약한 객실의 번호
        private String roomName; // 예약한 객실의 이름
        private LocalDate checkInDate; // 체크인 날짜
        private LocalDate checkOutDate; // 체크아웃 날짜

        public userReservationDTO(Reservation reservation, Room room) {
            this.reservationId = reservation.getId();
            this.userId = reservation.getUser().getId();
            this.stayName = room.getStay().getName();
            this.checkInDate = reservation.getCheckInDate();
            this.checkOutDate = reservation.getCheckOutDate();
            this.roomId = reservation.getRoom().getId();
            this.roomName = room.getName();
        }
    }

    // 관리자 페이지에서 특정 회원의 예약 상세보기 뷰에 필요한 데이터
    @Data
    public static class userReservationDetailDTO{
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
        private String reservationName; // 예약자 대표 이름
        private String reservationTel; // 예약자 대표 연락처
        private LocalDateTime payAt; // 결제 일자
        private Integer amount; // 결제 금액
        private String way; // 결제 수단

        public userReservationDetailDTO(Reservation reservation, Room room, Pay pay) {
            this.reservationId = reservation.getId();
            this.userId = reservation.getUser().getId();
            this.stayName = room.getStay().getName();
            this.stayAddress =  room.getStay().getAddress();
            this.checkInDate = reservation.getCheckInDate();
            this.checkInTime = room.getRoomInformation().getCheckIn();
            this.checkOutDate = reservation.getCheckOutDate();
            this.checkOutTime = room.getRoomInformation().getCheckOut();
            this.roomId = reservation.getRoom().getId();
            this.roomName = room.getName();
            this.reservationName = reservation.getReservationName();
            this.reservationTel = reservation.getReservationTel();
            this.payAt = pay.getCreatedAt();
            this.amount = pay.getAmount();
            this.way = pay.getWay();
        }
    }

    // 관리자 페이지에서 특정 개인의 리뷰찾기 응답 DTO
    @Data
    public static class userReviewListDTO{
        private Integer reviewId;
        private String stayName;
        private String content;
        private Integer score;
        private LocalDateTime createdAt;
        private List<ReviewResponse.Find> children = new ArrayList<>();

        public userReviewListDTO(Review review) {
            this.reviewId = review.getId();
            this.stayName = review.getStay().getName();
            this.content = review.getContent();
            this.score = review.getScore();
            this.createdAt = review.getCreatedAt();
        }
    }
}

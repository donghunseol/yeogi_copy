package com.example.final_project.admin;

import com.example.final_project._core.enums.CompanyEnum;
import com.example.final_project._core.enums.UserEnum;
import com.example.final_project.company.Company;
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
    public static class UserListDTO {
        private Integer userId; // 유저 번호
        private String name; // 회원 이름
        private String email; // 이메일
        private Integer reportCount; // 신고 받은 횟수
        private UserEnum state; // 상태 (ACTIVE : 회원 유지, QUIT : 회원 탈퇴, BLACK : 신고 받아서 제한된 회원)

        public UserListDTO(User user) {
            this.userId = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.reportCount = user.getReportCount();
            this.state = user.getState();
        }
    }

    // 관리자 페이지에서 출력할 유저 상세 정보
    @Data
    public static class UserDetailDTO {
        private Integer id; // 유저 번호
        private String email; // 이메일 (로그인 할 때 아이디로 사용)
        private String name; // 회원 이름
        private String phone; // 전화번호
        private UserEnum state; // 상태 (ACTIVE : 회원 유지, QUIT : 회원 탈퇴, BLACK : 신고 받아서 제한된 회원)
        private LocalDate birth; // 생년월일
        private Integer reportCount; // 신고 받은 횟수
        private LocalDateTime createdAt; // 유저 가입 일자

        public UserDetailDTO(User user) {
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
    public static class CompanyListDTO {
        private Integer companyId; // 기업 번호
        private String email; // 기업 이메일
        private String businessName; // 등록 상호명
        private String businessNumber; // 사업자 번호
        private String businessAddress; // 사업자 주소
        private String phone; // 사업자 전화번호
        private String name; // 사업자 이름
        private String stateMessage; // 상태 메시지 한글
        private String stateColor; // 상태 메시지 색
        private LocalDate createdAt; // 생성 일자
        Boolean isBlack; // true 면 블랙 등록 / false 면 블랙 미등록
        Boolean isBlackCancel; // true 면 블랙 취소 버튼 활성 / false 면 블랙 취소 버튼 비활성

        public CompanyListDTO(Company company) {
            this.companyId = company.getId();
            this.email = company.getEmail();
            this.businessName = company.getBusinessName();
            this.businessNumber = company.getBusinessNumber();
            this.businessAddress = company.getBusinessAddress();
            this.phone = company.getPhone();
            this.name = company.getName();
            this.createdAt = LocalDate.from(company.getCreatedAt());
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

    // 관리자 페이지에서 출력할 특정 기업의 상세 정보
    @Data
    public static class CompanyDetailDTO {
        private Integer companyId; // 기업 번호
        private String email; // 이메일
        private String businessName; // 등록 상호명
        private String businessNumber; // 사업자 번호
        private String businessAddress; // 사업자 주소
        private String name; // 사업자 이름
        private String phone; // 사업자 전화번호
        private LocalDateTime createdAt; // 기업 가입 일자
        private String stateMessage; // 상태 메시지 한글
        private Integer reportCount; // 신고 받은 횟수
        Boolean isBlack; // true 면 블랙 등록 / false 면 블랙 미등록

        public CompanyDetailDTO(Company company) {
            this.companyId = company.getId();
            this.email = company.getEmail();
            this.businessName = company.getBusinessName();
            this.businessNumber = company.getBusinessNumber();
            this.businessAddress = company.getBusinessAddress();
            this.name = company.getName();
            this.phone = company.getPhone();
            this.createdAt = company.getCreatedAt();
            isBlack = null;
            if (company.getState() == CompanyEnum.ACTIVE) {
                this.stateMessage = "승인";
                this.isBlack = false;
            } else if (company.getState() == CompanyEnum.PROGRESSING) {
                this.stateMessage = "대기중";
            } else if (company.getState() == CompanyEnum.QUIT) {
                this.stateMessage = "탈퇴";
            } else if (company.getState() == CompanyEnum.REJECT) {
                this.stateMessage = "거절";
            } else if (company.getState() == CompanyEnum.BLACK) {
                this.stateMessage = "블랙";
                this.isBlack = true;
            }
            this.reportCount = company.getReportCount();
        }
    }


    // 관리자 페이지에서 출력할 특정 기업의 숙소 정보
    @Data
    public static class CompanyStayListDTO {
        private Integer stayId; // 숙소 번호
        private String stayName; // 숙소 이름
        private String category; // 숙소 분류
        private String address; // 숙소 주소
        private Integer roomsSize; // 객실 갯수

        public CompanyStayListDTO(Stay stay, Integer roomsSize) {
            this.stayId = stay.getId();
            this.stayName = stay.getName();
            this.category = stay.getCategory();
            this.address = stay.getAddress();
            this.roomsSize = roomsSize;
        }
    }

    // 관리자 페이지에서 특정 회원의 예약 리스트 뷰에 필요한 데이터
    @Data
    public static class UserReservationDTO {
        private Integer reservationId; // 예약 번호
        private Integer userId; // 예약한 유저의 번호
        private String stayName; // 예약한 숙소의 이름
        private String stayAddress; // 숙소 주소
        private Integer roomId; // 예약한 객실의 번호
        private String roomName; // 예약한 객실의 이름
        private String reservationName; // 예약자 대표 이름
        private String reservationTel; // 예약자 대표 연락처
        private LocalDate checkInDate; // 체크인 날짜
        private LocalDate checkOutDate; // 체크아웃 날짜
        private LocalDateTime payAt; // 결제 일자
        private Integer amount; // 결제 금액
        private String way; // 결제 수단

        public UserReservationDTO(Reservation reservation, Room room, Pay pay) {
            this.reservationId = reservation.getId();
            this.userId = reservation.getUser().getId();
            this.stayName = room.getStay().getName();
            this.stayAddress = room.getStay().getAddress();
            this.checkInDate = reservation.getCheckInDate();
            this.checkOutDate = reservation.getCheckOutDate();
            this.roomId = room.getId();
            this.roomName = room.getName();
            this.reservationName = reservation.getReservationName();
            this.reservationTel = reservation.getReservationTel();
            this.payAt = pay.getCreatedAt();
            this.amount = pay.getAmount();
            this.way = pay.getWay();
        }
    }

    // 관리자 페이지에서 특정 회원의 예약 상세보기 뷰에 필요한 데이터
    @Data
    public static class UserReservationDetailDTO {
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

        public UserReservationDetailDTO(Reservation reservation, Room room, Pay pay) {
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
            this.reservationName = reservation.getReservationName();
            this.reservationTel = reservation.getReservationTel();
            this.payAt = pay.getCreatedAt();
            this.amount = pay.getAmount();
            this.way = pay.getWay();
        }
    }

    // 관리자 페이지에서 특정 개인의 리뷰찾기 응답 DTO
    @Data
    public static class UserReviewListDTO {
        private Integer reviewId;
        private String stayName;
        private String content;
        private Integer score;
        private LocalDateTime createdAt;
        private List<ReviewResponse.Find> children = new ArrayList<>();

        public UserReviewListDTO(Review review) {
            this.reviewId = review.getId();
            this.stayName = review.getStay().getName();
            this.content = review.getContent();
            this.score = review.getScore();
            this.createdAt = review.getCreatedAt();
        }
    }
}

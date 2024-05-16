package com.example.final_project.admin;

import com.example.final_project._core.enums.CompanyEnum;
import com.example.final_project._core.enums.FaqEnum;
import com.example.final_project._core.enums.QuestionEnum;
import com.example.final_project._core.enums.UserEnum;
import com.example.final_project.company.Company;
import com.example.final_project.faq.Faq;
import com.example.final_project.pay.Pay;
import com.example.final_project.question.Question;
import com.example.final_project.report.Report;
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
        private String stateMessage; // 상태 메시지 한글
        private String stateColor; // 상태 메시지 색
        Boolean isBlack; // true면 블랙 등록 / false면 블랙 미등록

        public UserListDTO(User user) {
            this.userId = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.reportCount = user.getReportCount();
            isBlack = false;
            if (user.getState() == UserEnum.ACTIVE) {
                this.stateMessage = "승인";
                this.stateColor = "approval";
            } else if (user.getState() == UserEnum.QUIT) {
                this.stateMessage = "탈퇴";
                this.stateColor = "drop-out";
            } else if (user.getState() == UserEnum.BLACK) {
                this.stateMessage = "블랙";
                this.stateColor = "black-list";
                this.isBlack = true;
            }
        }
    }

    // 관리자 페이지에서 출력할 유저 상세 정보
    @Data
    public static class UserDetailDTO {
        private Integer userId; // 유저 번호
        private String email; // 이메일 (로그인 할 때 아이디로 사용)
        private String name; // 회원 이름
        private String phone; // 전화번호
        private LocalDate birth; // 생년월일
        private LocalDateTime createdAt; // 유저 가입 일자
        private Integer reportCount; // 신고 받은 횟수
        private String stateMessage; // 상태 메시지 한글
        Boolean isBlack; // true면 블랙 등록 / false면 블랙 미등록

        public UserDetailDTO(User user) {
            this.userId = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.phone = user.getPhone();
            this.birth = user.getBirth();
            this.createdAt = user.getCreatedAt();
            this.reportCount = user.getReportCount();
            isBlack = false;
            if (user.getState() == UserEnum.ACTIVE) {
                this.stateMessage = "승인";
            } else if (user.getState() == UserEnum.QUIT) {
                this.stateMessage = "탈퇴";
            } else if (user.getState() == UserEnum.BLACK) {
                this.stateMessage = "블랙";
                this.isBlack = true;
            }

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
        private LocalDate createdAt; // 생성 일자
        private String stateMessage; // 상태 메시지 한글
        private String stateColor; // 상태 메시지 색
        Boolean isBlack; // true 면 블랙 등록 / false 면 블랙 미등록

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
        private Integer reportCount; // 신고 받은 횟수
        private String stateMessage; // 상태 메시지 한글
        Boolean isBlack; // true 면 블랙 등록 / false 면 블랙 미등록
        Boolean isProgressing; // true면 기업 승인거절 버튼 활성화 / false면 비활성화

        public CompanyDetailDTO(Company company) {
            this.companyId = company.getId();
            this.email = company.getEmail();
            this.businessName = company.getBusinessName();
            this.businessNumber = company.getBusinessNumber();
            this.businessAddress = company.getBusinessAddress();
            this.name = company.getName();
            this.phone = company.getPhone();
            this.createdAt = company.getCreatedAt();
            this.reportCount = company.getReportCount();
            isBlack = false;
            isProgressing = false;
            if (company.getState() == CompanyEnum.ACTIVE) {
                this.stateMessage = "승인";
            } else if (company.getState() == CompanyEnum.PROGRESSING) {
                this.stateMessage = "대기중";
                this.isProgressing = true;
            } else if (company.getState() == CompanyEnum.QUIT) {
                this.stateMessage = "탈퇴";
            } else if (company.getState() == CompanyEnum.REJECT) {
                this.stateMessage = "거절";
            } else if (company.getState() == CompanyEnum.BLACK) {
                this.stateMessage = "블랙";
                this.isBlack = true;
            }
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

    //관리자 페이지에서 기업 문의사항 리스트DTO
    @Data
    public static class CompanyQuestionListDTO{
        private Integer questionId;
        private Integer companyId;
        private String title;
        private String businessName;
        private String createdAt;
        private QuestionEnum state;
        private String stateColor;
        private String stateText;

        public CompanyQuestionListDTO(Company company, Question question) {
            this.questionId = question.getId();
            this.companyId = company.getId();
            this.title = question.getTitle();
            this.businessName = company.getBusinessName();
            this.createdAt = formatDate(question.getCreatedAt()); // 년월일만 문자열로 포맷팅
            this.state = question.getState();
            setStateColorAndText();
        }

        // 날짜 파싱 매서드
        private String formatDate(LocalDateTime dateTime) {
            // 년, 월, 일을 추출하여 문자열로 반환
            return String.format("%04d년 %02d월 %02d일", dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
        }
        // 상태 컬러 설정 매서드
        private void setStateColorAndText() {
            switch (state) {
                case WAIT:
                    stateColor = "wait"; // 대기중인 경우의 색상 클래스
                    stateText = "대기중";
                    break;
                case COMPLETION:
                    stateColor = "approval"; // 완료된 경우의 색상 클래스
                    stateText = "완료";
                    break;
                default:
                    stateColor = ""; // 기본값은 빈 문자열로 설정
                    break;
            }
        }
    }

    //관리자 페이지에서 기업 문의사항 디테일
    @Data
    public static class CompanyQuestionDetailDTO{
        private Integer questionid;
        private Integer companyId;
        private String title;
        private String content;
        private String businessName;
        private String createdAt;
        private String answer;

        public CompanyQuestionDetailDTO(Company company, Question question) {
            this.questionid = question.getId();
            this.companyId = company.getId();
            this.title = question.getTitle();
            this.content = question.getContent();
            this.businessName = company.getBusinessName();
            this.createdAt = formatDate(question.getCreatedAt());
            this.answer = question.getAnswer();

        }

    }

    // 날짜 자르는 매서드
    private static String formatDate(LocalDateTime dateTime) {
        // 년, 월, 일을 추출하여 문자열로 반환
        return String.format("%04d년 %02d월 %02d일", dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
    }

    //관리자 페이지에서 기업 FAQ 리스트
    @Data
    public static class adminFaqListDTO{
        private Integer faqId;
        private String writer;
        private String content;
        private FaqEnum classification;
        private String createdAt;

        public adminFaqListDTO(Faq faq) {
            this.faqId = faq.getId();
            this.content = faq.getContent();
            this.writer = faq.getAdmin().getName();
            this.createdAt = AdminResponse.formatDate(faq.getCreatedAt());
            this.classification = faq.getClassification();
        }
    }

//
//    @Data
//    public static class ReportList {
//        private Integer reportId; // 신고 번호
//        private String reportContent; // 신고 내용
//        private Integer userId; // 작성자의 회원 번호
//        private String username; // 작성자 이름
////        private
////        // 처리결과
////        private Integer reviewId; // 리뷰 번호
//        public ReportList(Report report, Review review) {
//            this.reportId = report.getReportId();
//            this.reportContent = report.getReportContent();
////            this.reviewId = review.getId();
//        }
//    }


    //관리자 페이지에서 기업 FAQ 디테일
    @Data
    public static class adminFaqDetail{
        private String createdAt;
        private String reply;
        private String writer;
        private String content;

        public adminFaqDetail(Faq faq) {
            this.content = faq.getContent();
            this.createdAt = AdminResponse.formatDate(faq.getCreatedAt());
            this.reply = faq.getReply();
            this.writer = faq.getAdmin().getName();
        }
    }

}

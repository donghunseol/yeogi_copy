package com.example.final_project.review;

import com.example.final_project._core.enums.ReviewEnum;
import com.example.final_project.user.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewResponse {

    //리뷰작성 응답 DTO
    @Data
    public static class Save{
        private Integer id;
        private UserDTO writer;
        private String content;
        private Integer score;
        private List<Save> children = new ArrayList<>();

        public Save(Integer id, UserDTO writer, String content, Integer score) {
            this.id = id;
            this.writer = writer;
            this.content = content;
            this.score = score;
        }

        @Data
        public static class UserDTO{ ;
            private Integer id;
            private String email;
            private String name;
            private Integer reportCount;

            public UserDTO(User writer) {
                this.id = writer.getId();
                this.email = writer.getEmail();
                this.name = writer.getName();
                this.reportCount = writer.getReportCount();
            }
        }

//        public static ReviewResponse.Save convertReviewToDTO(Review review){
//            return review.getState().equals("FLAWLESS") ?
//                    new ReviewResponse.Save(review.getId(),null,"삭제된 댓글입니다.",null) :
//                    new ReviewResponse.Save(review.getId() , new UserDTO(review.getUser()), review.getContent(), review.getScore());
//        }

    }

    //리뷰찾기 응답 DTO
    @Data
    public static class Find{
        private Integer reviewCount;
        private Integer id;
        private UserDTO writer;
        private String content;
        private LocalDateTime createdAt;
        private Integer score;
        private List<Find> children = new ArrayList<>();


        public Find(Integer id , UserDTO writer, String content, LocalDateTime createdAt, Integer score, Integer reviewCount) {
            this.id = id;
            this.writer = writer;
            this.content = content;
            this.createdAt = createdAt;
            this.score = score;
            this.reviewCount = reviewCount;
        }

        @Data
        public static class UserDTO{
            private String email;
            private String name;
            private String state;
            private Integer reportCount;


            public UserDTO(User user) {
                this.email = user.getEmail();
                this.name = user.getName();
                this.state = String.valueOf(user.getState());
                this.reportCount = user.getReportCount();
            }
        }
    }

    //리뷰삭제 응답 DTO
    @Data
    public static class Delete{
        private ReviewEnum state;

        public Delete(ReviewEnum state) {
            this.state = state;
        }
    }

    //리뷰디테일 응답 DTO
    @Data
    public static class Detail{
        private Integer id;
        private UserDTO writer;
        private String content;
        private String name;
        private Integer stayId;
        private Review parent;
        private LocalDateTime createdAt;
        private Integer score;
        private List<Detail> children = new ArrayList<>();

        public Detail(Review review ,UserDTO writer) {
            this.id = review.getId();
            this.writer = writer;
            this.name = review.getStay().getName();
            this.stayId = review.getStay().getId();
            this.parent = review.getParent();
            this.content = review.getContent();
            this.createdAt = review.getCreatedAt();
            this.score = review.getScore();
        }
        @Data
        public static class UserDTO{
            private String name;
            private String state;
            private Integer reportCount;

            public UserDTO(User user) {
                this.name = user.getName();
                this.state = String.valueOf(user.getState());
                this.reportCount = user.getReportCount();
            }

        }
    }

    // 댓글 신고 폼
    @Data
    public static class ReportForm{
        private Integer id;
        private Integer userId;
        private Integer stayId;
        private Integer reviewId;
        private String content;
        public ReportForm(Review review) {
            this.id = review.getId();
            this.stayId = review.getStay().getId();
            this.userId = review.getUser().getId();
            this.reviewId = review.getId();
            this.content = review.getContent();
        }
    }

    // 댓글 신고응답 DTO
    @Data
    public static class Report{
        private Integer id;
        private ReviewDTO review;
        private ReviewEnum result;
        private LocalDateTime createdAt;
        private String reportContent;

        public Report(Report report, ReviewDTO review) {
            this.id = report.getId();
            this.review = review;
            this.result = report.getResult();
            this.createdAt = report.getCreatedAt();
            this.reportContent = report.getReportContent();
        }


        @Data
        public static class ReviewDTO{
            private Integer id;
            private Integer userId;
            private Integer stayId;
            private String content;
            private Integer score;

            public ReviewDTO(Review review) {
                this.id = review.getId();
                this.userId = review.getUser().getId();
                this.stayId = review.getStay().getId();
                this.content = review.getContent();
                this.score = review.getScore();
            }
        }
    }

    // 유저가 적은 리뷰 리스트 응답DTO
    @Data
    public static class ReveiwListDTO{
        private Integer reviewId;
        private String stayName;
        private String content;
        private Integer score;
        private String createdAt;

        public ReveiwListDTO(Review review) {
            this.reviewId = review.getId();
            this.stayName = review.getStay().getName();
            this.content = review.getContent();
            this.score = review.getScore();
            this.createdAt = formatDate(review.getCreatedAt());
        }

        private String formatDate(LocalDateTime dateTime) {
            // 년, 월, 일을 추출하여 문자열로 반환
            return String.format("%04d년 %02d월 %02d일", dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
        }
    }

}

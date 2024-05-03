package com.example.final_project.review;

import com.example.final_project._core.enums.ReviewEnum;
import com.example.final_project.company.Company;
import com.example.final_project.stay.Stay;
import com.example.final_project.user.User;
import lombok.Data;

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
            private String phone;
            private Integer reportCount;

            public UserDTO(User writer) {
                this.id = writer.getId();
                this.email = writer.getEmail();
                this.name = writer.getName();
                this.phone = writer.getPhone();
                this.reportCount = writer.getReportCount();
            }
        }

        public static ReviewResponse.Save convertReviewToDTO(Review review){
            return review.getIsDelete().equals("COMPLETE") ?
                    new ReviewResponse.Save(review.getId(),null,"삭제된 댓글입니다.",null) :
                    new ReviewResponse.Save(review.getId() , new UserDTO(review.getWriter()), review.getContent(), review.getScore());
        }

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
        private Review parent;
        private LocalDateTime createdAt;
        private Integer score;
        private List<Detail> children = new ArrayList<>();

        public Detail(Review review ,UserDTO writer) {
            this.id = review.getId();
            this.writer = writer;
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


}

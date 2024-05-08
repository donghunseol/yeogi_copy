package com.example.final_project.review;

import com.example.final_project._core.enums.ReportEnum;
import com.example.final_project._core.enums.ReviewEnum;
import com.example.final_project.report.Report;
import com.example.final_project.stay.Stay;
import com.example.final_project.user.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewRequest {

    //댓글 작성 DTO(insert)
    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReviewRequestDTO {
        private Integer userId;
        private Integer parentId;
        private String content;
        private Integer stayId;
        private Integer score;
        private ReviewEnum isDelete;
        public Review toEntity(User user ,Stay stay){
            return Review.builder()
                    .user(user)
                    .stay(stay)
                    .isDelete(isDelete)
                    .score(score)
                    .content(content)
                    .build();
        }
    }

    //댓글 신고 DTO(insert)
    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReportRequestDTO{
        private Integer userId;
        private Integer stayId;
        private String reportContent;
        private Integer reviewId;
        private ReportEnum result;
        private LocalDateTime createdAt;

        public Report toEntity(User user, Review review){
            return Report.builder()
                    .stay(review.getStay())
                    .user(user)
                    .review(review)
                    .reportContent(reportContent)
                    .result(result)
                    .createdAt(createdAt)
                    .build();
        }
    }

}

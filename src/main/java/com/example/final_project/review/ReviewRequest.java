package com.example.final_project.review;

import com.example.final_project._core.enums.ReportEnum;
import com.example.final_project._core.enums.ReviewEnum;
import com.example.final_project.company.Company;
import com.example.final_project.company.SessionCompany;
import com.example.final_project.report.Report;
import com.example.final_project.stay.Stay;
import com.example.final_project.user.SessionUser;
import com.example.final_project.user.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewRequest {




    //대댓글 작성 DTO(insert)
    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReviewRequestDTO {
        private Integer userId; // 사용자 ID
        private Integer parentId; // 부모 댓글 ID
        private String content; // 내용
        private Integer stayId; // 숙소 ID
        private Integer score; // 평점
        private ReviewEnum isDelete; // 삭제 여부

        // SessionUser 또는 SessionCompany를 받을 수 있도록 수정
        public Review toEntity(Object sessionObject, Stay stay) {
            if (sessionObject instanceof SessionUser) {
                SessionUser sessionUser = (SessionUser) sessionObject;
                User user = new User();
                user.setId(sessionUser.getId());
                return Review.builder()
                        .user(user)
                        .stay(stay)
                        .isDelete(isDelete)
                        .score(score)
                        .content(content)
                        .build();
            } else if (sessionObject instanceof SessionCompany) {
                SessionCompany sessionCompany = (SessionCompany) sessionObject;
                Company company = new Company();
                company.setId(sessionCompany.getId());
                return Review.builder()
                        .company(company) // 기업 정보를 저장
                        .stay(stay)
                        .isDelete(isDelete)
                        .score(score)
                        .content(content)
                        .build();
            } else {
                throw new IllegalArgumentException("Invalid session object type");
            }
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
//                    .user(user)
                    .review(review)
                    .reportContent(reportContent)
                    .result(result)
                    .createdAt(createdAt)
                    .build();
        }
    }

}

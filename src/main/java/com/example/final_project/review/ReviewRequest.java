package com.example.final_project.review;

import com.example.final_project.stay.Stay;
import com.example.final_project.user.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReviewRequest {
    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReviewRequestDTO {
        private Integer userId;
        private Integer parentId;
        private String content;
        private Integer stayId;
        private Integer score;

        public Review toEntity(User user ,Stay stay){
            return Review.builder()
                    .user(user)
                    .stay(stay)
                    .score(score)
                    .content(content)
                    .build();
        }
    }
}

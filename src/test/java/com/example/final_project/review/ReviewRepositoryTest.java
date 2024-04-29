package com.example.final_project.review;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void findByUserIdWithUserAndRoom_test(){
        // given
        Integer userId = 1;

        // when
        List<Review> reviewList = reviewRepository.findByUserIdWithUserAndRoom(userId);

        // eye
        System.out.println("findByUserIdWithUserAndRoom_test size : " + reviewList.size());

        // then
        Assertions.assertThat(reviewList.getFirst().getContent()).isEqualTo("정말 좋았어요!");

    }
}

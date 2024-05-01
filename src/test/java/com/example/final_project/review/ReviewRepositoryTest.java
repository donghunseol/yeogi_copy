package com.example.final_project.review;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


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

    @Test
    public void findAllByStayId_test(){
        //given
        Integer stayId = 1;
        //when
        List<Review> reviewList = reviewRepository.findAllByStayIdWithDetails(stayId);
        //then
        Assertions.assertThat(reviewList.getFirst().getContent()).isEqualTo("정말 좋았어요!");
    }


    @Test
    public void findReviewByIdWithParent_test(){
        //given
        Integer id = 1;
        //when
        Review review = reviewRepository.findReviewByIdWithParent(id);
        //eye
        System.out.println(review.getParent());

    }
}

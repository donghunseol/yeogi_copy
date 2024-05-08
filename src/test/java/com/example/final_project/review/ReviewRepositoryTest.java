package com.example.final_project.review;

import com.example.final_project._core.errors.exception.Exception404;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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

    @Test
    public void findByReviewId_test() {
        // given
        Integer revieId = 1;
        // when
        Review review =reviewRepository.findByReviewId(revieId);
        // eye

        // then
        Assertions.assertThat(review.getContent()).isEqualTo("정말 좋았어요!");
    }

}

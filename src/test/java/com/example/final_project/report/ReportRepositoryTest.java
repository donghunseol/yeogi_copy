package com.example.final_project.report;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    @Test
    public void findAllWithReviewAndUserAndStay_test(){
        // given


        // when
        List<Report> reportList= reportRepository.findAllWithReviewAndUserAndStay();

        // eye
        System.out.println("findAllWithReviewAndUserAndStay_test size : " + reportList.size());

        // then


    }

    @Test
    public void findByIdWithReviewAndUserAndStay_test(){
        // given
        Integer reportedId = 2;

        // when
        Report resp = reportRepository.findByIdWithReviewAndUserAndStay(reportedId);

        // eye
        System.out.println("findByIdWithReviewAndUserAndStay_test reportId : " + resp.getReportId());
        System.out.println("findByIdWithReviewAndUserAndStay_test reviewId : " + resp.getReview().getId());
        System.out.println("findByIdWithReviewAndUserAndStay_test stayId : " + resp.getReview().getStay().getId());
        System.out.println("findByIdWithReviewAndUserAndStay_test userId : " + resp.getUser().getId());
        System.out.println("findByIdWithReviewAndUserAndStay_test reviewUserId : " + resp.getReview().getUser().getId());


        // then

    }

    @Test
    public void testfindReportDetailById_test(){
        // given
        Integer reportedId = 2;

        // when
        Report resp = reportRepository.findByIdWithReviewAndUserAndStay(reportedId);

        // eye
        System.out.println("findByIdWithReviewAndUserAndStay_test reportId : " + resp.getReportId());
        System.out.println("findByIdWithReviewAndUserAndStay_test reviewId : " + resp.getReview().getId());
        System.out.println("findByIdWithReviewAndUserAndStay_test stayId : " + resp.getReview().getStay().getId());
        System.out.println("findByIdWithReviewAndUserAndStay_test userId : " + resp.getUser().getId());
        System.out.println("findByIdWithReviewAndUserAndStay_test reviewUserId : " + resp.getReview().getUser().getId());


        // then

    }
}

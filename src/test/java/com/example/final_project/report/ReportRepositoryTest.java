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
}

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
    public void findByCompanyId_test(){
        // given
        Integer companyId = 1;

        // when
        List<Report> reportList = reportRepository.findByCompanyId(companyId);

        // eye
        System.out.println("findByCompanyId_test size : " + reportList.size());

        // then


    }
}

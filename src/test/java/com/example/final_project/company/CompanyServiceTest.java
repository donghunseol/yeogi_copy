package com.example.final_project.company;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Test
    public void companyStayList_test(){
        // given
        Integer companyId = 1;

        // when
        List<CompanyResponse.companyStayListDTO> companyStayList = companyService.companyStayList(companyId);

        // eye
        System.out.println("companyStayList_test size : " + companyStayList.size());
        System.out.println("companyStayList_test getFirst : " + companyStayList.getFirst());
        System.out.println("companyStayList_test getLast : " + companyStayList.getLast());

        // then
        Assertions.assertThat(companyStayList.getLast().getImagePath()).isEqualTo("/images/hotel.png");

    }

    @Test
    public void companyStayDetailList_test(){
        // given
        Integer stayId = 1;

        // when
        List<CompanyResponse.companyStayDetailDTO> companyStayDetailList = companyService.companyStayDetailList(stayId);

        // eye
        System.out.println("companyStayDetailList_test getFirst : " + companyStayDetailList.getFirst());

        // then


    }
}
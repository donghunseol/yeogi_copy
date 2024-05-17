package com.example.final_project.option;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class OptionRepositoryTest {

    @Autowired
    OptionRepository  optionRepository;

    @Test
    public void findByStayId_test(){
        //given
        Integer stayId = 1;
        //when
        List<Option> optionList = optionRepository.findByStayId(stayId);
        //then
//        System.out.println("결과값================================" + optionList.size());
    }

}

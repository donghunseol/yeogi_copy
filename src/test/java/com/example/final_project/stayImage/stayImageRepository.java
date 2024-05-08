package com.example.final_project.stayImage;

import com.example.final_project.stay.StayRepository;
import com.example.final_project.stay_image.StayImage;
import com.example.final_project.stay_image.StayImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class stayImageRepository {

    @Autowired
    StayImageRepository stayImageRepository;


    @Test
    public void findByStayId_test() {
            // given
            Integer stayId = 1;
            // when
            List<StayImage> stayImage = stayImageRepository.findByStayId(stayId);
            // eye
            System.out.println(stayImage.size());
            // then
        }

}

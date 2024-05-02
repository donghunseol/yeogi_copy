package com.example.final_project.room;

import com.example.final_project.company.CompanyResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;


    @Test
    public void findByStayId_test(){
        // given
        Integer stayId = 1;
        // when
        List<Room> roomList = roomRepository.findByStayId(stayId);
        // then
        System.out.println("findByStayId_test size : " + roomList.size());
        System.out.println("findByStayId_test getFirst.id : " + roomList.getFirst().getId());
        System.out.println("findByStayId_test getLast.id : " + roomList.getLast().getId());
    }

    @Test
    public void countByStayIdAndTier_test(){
        // given
        Integer stayId = 1;

        // when
        List<Room> roomList = roomRepository.findByStayId(stayId);
        List<CompanyResponse.roomCountByStayIdAndTierDTO> respDTO = roomList.stream().map(room -> {
            return new CompanyResponse.roomCountByStayIdAndTierDTO(room.getTier(), stayId);
        }).toList();

        // eye
        System.out.println("countByStayIdAndTier_test : " + respDTO);

        // then
        Assertions.assertThat(respDTO.getFirst().getTierCount()).isEqualTo(1);

    }
}

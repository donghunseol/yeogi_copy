package com.example.final_project.room_information;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalTime;

@DataJpaTest
public class RoomInformationRepositoryTest {

    @Autowired
    private RoomInformationRepository roomInformationRepository;

    @Test
    public void findByRoomId_test(){
        // given
        Integer roomId = 1;

        // when
        RoomInformation roomInformation = roomInformationRepository.findByRoomId(roomId);

        // eye
        // 그냥 roomInformation을 출력하면 stack over flow가 일어나니까 주의
        System.out.println("findByRoomId_test id : "+roomInformation.getId());

        // then
        Assertions.assertThat(roomInformation.getCheckIn()).isEqualTo(LocalTime.of(15,0));

    }

}

package com.example.final_project.room;

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
}

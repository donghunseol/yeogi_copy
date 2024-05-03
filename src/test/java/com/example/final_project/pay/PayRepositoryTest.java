package com.example.final_project.pay;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class PayRepositoryTest {

    @Autowired
    PayRepository payRepository;

    @Test
    public void findByRoomId_test() {
        // given
        Integer roomId = 1;
        LocalDate now = LocalDate.of(2023, 12, 31);

        // when
        Pay pay = payRepository.findByRoomId(roomId, now);

        // eye
        System.out.println("findByRoomId_test pay : " + pay.getWay());
        System.out.println("findByRoomId_test getReservation_id : " + pay.getReservation().getId());

        // then
        Assertions.assertThat(pay.getAmount()).isEqualTo(150000);

    }

    // 전체 수익 조회 쿼리 테스트
    @Test
    public void findTotalIncome_test() {
        // given
        Integer companyId = 3;
        // PayEnum state = PayEnum.COMPLETION; // PROCESSING : 처리 중, COMPLETION : 완료, REFUND : 환불

        // when
        PayResponse.TotalIncomeDTO respDTO = payRepository.findTotalIncome(companyId);

        // 만약 조회되는 데이터가 없는 경우
        if (respDTO == null) {
            respDTO = new PayResponse.TotalIncomeDTO(companyId, 0L, 0L);
        }

        // eye
        System.out.println("findByTotalIncome_test : " + respDTO);

        // then
        Assertions.assertThat(respDTO.getTotalIncome()).isEqualTo(760000);
    }

    // 숙소 수익 조회 쿼리 테스트
    @Test
    public void findIncomeByStay_test() {
        // given
        Integer companyId = 1;
        Integer stayId = 1;
        // PayEnum state = PayEnum.COMPLETION; // PROCESSING : 처리 중, COMPLETION : 완료, REFUND : 환불

        // when
        PayResponse.StayTotalIncomeDTO respDTO = payRepository.findIncomeByStay(companyId, stayId);

        // 만약 조회되는 데이터가 없는 경우
        if (respDTO == null) {
            respDTO = new PayResponse.StayTotalIncomeDTO(companyId, stayId, 0L, 0L);
        }

        // eye
        System.out.println("findByTotalIncome_test/stayId/1 : " + respDTO);

        // then
        Assertions.assertThat(respDTO.getTotalIncome()).isEqualTo(400000);
    }
}

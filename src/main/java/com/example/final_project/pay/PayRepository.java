package com.example.final_project.pay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface PayRepository extends JpaRepository<Pay, Integer> {

    @Query("select p from Pay p where p.reservation.id = :reservationId")
    Optional<Pay> findByReservationId(@Param("reservationId") Integer reservationId);

    @Query("SELECT p, r FROM Pay p JOIN FETCH Reservation r ON p.reservation.id = r.id JOIN FETCH Room ro ON r.room.id = ro.id WHERE p.reservation.room.id = :roomId AND r.checkInDate = :now")
    Pay findByRoomId(@Param("roomId") Integer roomId, @Param("now") LocalDate now);

    // 전체 수익 조회
    @Query("""
            SELECT new com.example.final_project.pay.PayResponse$TotalIncomeDTO
            (p.reservation.room.stay.company.id, SUM(p.amount), count (p.reservation.id))
            FROM Pay p
            JOIN FETCH Reservation re ON p.reservation.id = re.id
            JOIN FETCH Room r ON re.room.id = r.id
            JOIN FETCH Stay s ON r.stay.id = s.id
            JOIN FETCH Company c ON s.company.id = c.id
            WHERE c.id = :companyId AND p.state = 'COMPLETION'
            """)
    PayResponse.TotalIncomeDTO findTotalIncome(@Param("companyId") Integer companyId);

    // 숙소 수익 조회
    @Query("""
            SELECT new com.example.final_project.pay.PayResponse$StayTotalIncomeDTO
            (p.reservation.room.stay.company.id, p.reservation.room.stay.id, SUM(p.amount), count (p.reservation.id))
            FROM Pay p
            JOIN FETCH Reservation re ON p.reservation.id = re.id
            JOIN FETCH Room r ON re.room.id = r.id
            JOIN FETCH Stay s ON r.stay.id = s.id
            JOIN FETCH Company c ON s.company.id = c.id
            WHERE c.id = :companyId AND p.state = 'COMPLETION' AND s.id = :stayId
            GROUP BY p.reservation.room.stay.company.id, p.reservation.room.stay.id
            """)
    PayResponse.StayTotalIncomeDTO findIncomeByStay(@Param("companyId") Integer companyId, @Param("stayId") Integer stayId);
}

package com.example.final_project.stay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StayRepository extends JpaRepository<Stay, Integer> {

    @Query("select s from Stay s left join fetch s.options o where s.id = :stayId")
    Optional<Stay> findByStayId(@Param("stayId") Integer stayId);

    // [숙소 관리] 로그인한 기업이 등록한 숙소 조회
    @Query("SELECT DISTINCT s FROM Stay s JOIN FETCH s.company c LEFT JOIN FETCH s.options o WHERE c.id = :companyId")
    List<Stay> findByCompanyId(@Param("companyId") Integer companyId);

    // 숙소 검색 (이름, 지역, 날짜, 가격, 인원 수, 예약 날짜 별 검색)
    @Query("""
            SELECT DISTINCT s FROM Stay s
                 LEFT JOIN FETCH s.rooms r
                 LEFT JOIN FETCH r.roomInformation ri
                 LEFT JOIN Reservation re ON r.id = re.room.id
                 WHERE (:stayName IS NULL OR s.name LIKE %:stayName%)
                 AND (:stayArea IS NULL OR s.address LIKE %:stayArea%)
                 AND (:roomPrice IS NULL OR r.price <= :roomPrice)
                 AND (:person IS NULL OR :person <= ri.maxPerson)
                 AND (
                       :startDate IS NULL OR :endDate IS NULL
                       OR NOT EXISTS (
                           SELECT 1 FROM Reservation reCheck
                           WHERE reCheck.room.id = r.id
                           AND (reCheck.checkInDate < :endDate AND reCheck.checkOutDate > :startDate)
                      )
                )
            """)
    List<Stay> findBySearchStay(@Param("stayName") String stayName,
                                @Param("stayArea") String stayArea,
                                @Param("roomPrice") Integer roomPrice,
                                @Param("person") Integer person,
                                @Param("startDate") LocalDate startDate,
                                @Param("endDate") LocalDate endDate);
}

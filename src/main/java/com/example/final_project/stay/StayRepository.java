package com.example.final_project.stay;

import com.example.final_project._core.enums.RoomEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StayRepository extends JpaRepository<Stay, Integer> {

    @Query("select s from Stay s left join fetch s.options o where s.id = :stayId")
    Optional<Stay> findByStayId(@Param("stayId") Integer stayId);

    // [숙소 관리] 로그인한 기업이 등록한 숙소 조회
    @Query("SELECT DISTINCT s FROM Stay s JOIN FETCH s.company c LEFT JOIN FETCH s.options o WHERE c.id = :companyId")
    List<Stay> findByCompanyId(@Param("companyId") Integer companyId);


    // 숙소 검색 (이름, 지역, 날짜, 가격, 인원 수 검색)
    // SELECT 1 FROM Reservation reCheck
    // WHERE reCheck.room.id = r.id
    // AND (reCheck.checkInDate < :endDate AND reCheck.checkOutDate > :startDate)
    // 체크용 로직
    @Query("""
            SELECT DISTINCT s FROM Stay s
            JOIN FETCH s.rooms r
            JOIN FETCH r.roomInformation ri
            WHERE (:stayName IS NULL OR s.name LIKE CONCAT('%', :stayName, '%'))
            AND (:stayArea IS NULL OR s.address LIKE CONCAT(:stayArea, '%'))
            AND (:roomPrice IS NULL OR r.price <= :roomPrice)
            AND (:person IS NULL OR :person <= ri.maxPerson)
            """)
    List<Stay> findBySearchStay(@Param("stayName") String stayName,
                                @Param("stayArea") String stayArea,
                                @Param("roomPrice") Integer roomPrice,
                                @Param("person") Integer person);

    // [숙소] 해외리스트
    @Query("SELECT s from Stay s LEFT JOIN FETCH s.options opt LEFT JOIN s.rooms ro where s.category = '해외'")
    List<Stay> findStayByOversea();

    // [숙소] 호텔리스트
    @Query("SELECT s from Stay s LEFT JOIN FETCH s.options opt LEFT JOIN s.rooms ro where s.category = '호텔'")
    List<Stay> findStayByHotel();

    // [숙소] 캠핑리스트
    @Query("SELECT s from Stay s LEFT JOIN FETCH s.options opt LEFT JOIN s.rooms ro where s.category = '캠핑'")
    List<Stay> findStayByCamping();

    // [숙소] 모텔리스트
    @Query("SELECT s from Stay s LEFT JOIN FETCH s.options opt LEFT JOIN s.rooms ro where s.category = '모텔'")
    List<Stay> findStayByMotel();

    // [숙소] 펜션리스트
    @Query("SELECT s from Stay s LEFT JOIN FETCH s.options opt LEFT JOIN s.rooms ro where s.category = '펜션'")
    List<Stay> findStayByPention();

    // [숙소] 홈 & 빌라리스트
    @Query("SELECT s from Stay s LEFT JOIN FETCH s.options opt LEFT JOIN s.rooms ro where s.category = '홈&빌라'")
    List<Stay> findStayByHomeAndVilla();

    // [숙소] 게하리스트
    @Query("SELECT s from Stay s LEFT JOIN FETCH s.options opt LEFT JOIN s.rooms ro where s.category = '게스트하우스'")
    List<Stay> findStayByGuesthouse();

    // [숙소] 특가리스트
    @Query("SELECT DISTINCT s FROM Stay s " +
            "LEFT JOIN s.options opt " +  // options 컬렉션은 기본적으로 LAZY 로딩
            "LEFT JOIN s.rooms ro " +      // rooms 컬렉션은 기본적으로 LAZY 로딩
            "WHERE ro.specialState = :APPLIED")
    List<Stay> findStayBySale(@Param("APPLIED") RoomEnum APPLIED);


    @Query("SELECT s FROM Stay s LEFT JOIN FETCH s.rooms ro WHERE ro.id = :roomId")
    Optional<Stay> findStayByRoom(@Param("roomId") Integer roomId);

}

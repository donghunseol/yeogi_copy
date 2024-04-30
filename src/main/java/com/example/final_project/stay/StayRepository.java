package com.example.final_project.stay;

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

}

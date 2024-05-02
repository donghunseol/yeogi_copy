package com.example.final_project.room;

import com.example.final_project.company.CompanyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("select r from Room r join fetch Stay s on r.stay.id = s.id  join fetch RoomInformation ri on r.roomInformation.id = ri.id where s.id = :stayId order by r.id")
    List<Room> findByStayId(@Param("stayId") Integer stayId);

    // [숙소 관리 - 숙소 상세보기] 로그인한 기업이 등록한 특정 숙소 상세보기(bottom 부분 데이터)
    // 빨간 선이 생기는게 정상! DTO 경로가 잘 인식된다.
    @Query("SELECT new com.example.final_project.company.CompanyResponse$companyStayDetailDTO(r, COUNT(r)) FROM Room r JOIN r.stay s WHERE s.id = :stayId GROUP BY r.tier")
    List<CompanyResponse.companyStayDetailDTO> findAndCountByStayId(@Param("stayId") Integer stayId);

    // [숙소 관리 - 숙소 상세보기 - 객실 상세보기] 로그인한 기업이 등록한 특정 숙소의 객실 상세보기
    @Query("SELECT r FROM Room r JOIN r.stay s JOIN r.roomInformation ri WHERE s.id = :stayId AND r.tier = :tier")
    List<Room> findByStayIdAndTier(@Param("stayId") Integer stayId, @Param("tier") String tier);
}

package com.example.final_project.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Integer> {

    @Query("select o from Option o join fetch Stay s on o.stay.id = s.id where s.id = :stayId order by o.id desc")
    List<Option> findByStayId(@Param("stayId") Integer stayId);



    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Option o where o.stay.id = :stayId")
    void deleteBystayId(@Param("stayId") Integer stayId);
}

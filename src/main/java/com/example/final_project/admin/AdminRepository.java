package com.example.final_project.admin;

import com.example.final_project.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {


    // 로그인 인증 쿼리
    @Query("select a from Admin a where a.name = :name and a.password = :password")
    Optional<Admin> findByIdAndPassword(@Param("name") String name, @Param("password") String password);
}

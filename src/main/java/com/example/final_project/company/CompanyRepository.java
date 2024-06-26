package com.example.final_project.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // 회원가입
    @Query("select c from Company c where c.id = :companyId")
    Optional<Company> findByCompanyId(@Param("companyId") Integer companyId);


    // 로그인 인증 쿼리
    @Query("select c from Company c where c.email = :email and c.password = :password")
    Optional<Company> findByIdAndPassword(@Param("email") String email, @Param("password") String password);

    // StayId로 Company찾기 쿼리
    @Query("select s.company from Stay s where s.id = :stayId")
    Optional<Company> findByStayId(@Param("stayId") Integer stayId);

    //이메일 중복 확인
    Company findByEmail(@Param("email") String email);

    // 키워드로 기업찾기
    @Query("""
            select c 
            from Company c  
            where c.name like %:keyword% or c.businessName like %:keyword%
            """)
    List<Company> findAllKeyword(@Param("keyword") String keyword);
}

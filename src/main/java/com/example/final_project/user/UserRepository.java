package com.example.final_project.user;

import com.example.final_project.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 로그인시 - 이메일, 비밀번호 조회
    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    // 회원 가입
    Optional<User> findByEmail(@Param("email") String email);

    // 키워드로 유저찾기
    @Query("""
            select u 
            from User u  
            where u.name like %:keyword% or u.email like %:keyword%
            """)
    List<User> findAllKeyword(@Param("keyword") String keyword);
}

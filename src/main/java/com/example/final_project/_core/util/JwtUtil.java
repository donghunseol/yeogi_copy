package com.example.final_project._core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.final_project.admin.Admin;
import com.example.final_project.admin.SessionAdmin;
import com.example.final_project.company.Company;
import com.example.final_project.company.SessionCompany;
import com.example.final_project.user.SessionUser;
import com.example.final_project.user.User;

import java.util.Date;

public class JwtUtil {

    // user 토큰 생성
    public static String userCreate(User user) {
        String jwt = JWT.create()
                .withSubject("stay")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L * 365L)) // 1년간 지속
                .withClaim("id", user.getId())
                .withClaim("email", user.getEmail())
                .withClaim("role", "user")
                .sign(Algorithm.HMAC512("yeoeotteohno")); // 대칭키 사용 나중에 yeoeotteohno 이라 적은 자리에 환경 변수를 넣는다 OS 의 값을 땡겨와야한다!
        return jwt;
    }

    // user 토큰 검증
    public static SessionUser userVerify(String jwt) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("yeoeotteohno")).build().verify(jwt);
        int id = decodedJWT.getClaim("id").asInt();
        String email = decodedJWT.getClaim("email").asString();

        // 임시 세션을 이용
        return SessionUser.builder()
                .id(id)
                .email(email)
                .build();
    }

    // company 토큰 생성
    public static String companyCreate(Company company) {
        String jwt = JWT.create()
                .withSubject("stay")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L * 365L)) // 1년간 지속
                .withClaim("id", company.getId())
                .withClaim("email", company.getEmail())
                .withClaim("role", "company")
                .sign(Algorithm.HMAC512("yeoeotteohno")); // 대칭키 사용 나중에 yeoeotteohno 이라 적은 자리에 환경 변수를 넣는다 OS 의 값을 땡겨와야한다!
        return jwt;
    }

    // company 토큰 검증
    public static SessionCompany companyVerify(String jwt) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("yeoeotteohno")).build().verify(jwt);
        int id = decodedJWT.getClaim("id").asInt();
        String email = decodedJWT.getClaim("email").asString();

        // 임시 세션을 이용
        return SessionCompany.builder()
                .id(id)
                .email(email)
                .build();
    }

    // admin 토큰 생성
    public static String adminCreate(Admin admin) {
        String jwt = JWT.create()
                .withSubject("stay")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L * 365L)) // 1년간 지속
                .withClaim("id", admin.getId())
                .withClaim("name", admin.getName())
                .withClaim("role", "admin")
                .sign(Algorithm.HMAC512("yeoeotteohno")); // 대칭키 사용 나중에 yeoeotteohno 이라 적은 자리에 환경 변수를 넣는다 OS 의 값을 땡겨와야한다!
        return jwt;
    }

    // admin 토큰 검증
    public static SessionAdmin adminVerify(String jwt) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("yeoeotteohno")).build().verify(jwt);
        int id = decodedJWT.getClaim("id").asInt();
        String name = decodedJWT.getClaim("name").asString();

        // 임시 세션을 이용
        return SessionAdmin.builder()
                .id(id)
                .name(name)
                .build();
    }
}
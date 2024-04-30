package com.example.final_project.company;

import com.example.final_project._core.errors.exception.Exception400;
import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.user.SessionUser;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;


    //로그인
    @Transactional
    public Company login(CompanyRequest.LoginDTO reqDTO){
        //1. 아이디 체크
        Company sessionUser = companyRepository.findByIdAndPassword(reqDTO.getEmail(),reqDTO.getPassword())
                .orElseThrow( () -> new Exception404("아이디 및 패스워드가 일치하지않습니다"));

//        String jwt = JwtUtil.companyCreate(sessionUser);
//        return jwt;

        return sessionUser;
    }

//    // 회원가입
//    @Transactional
//    public Company join(CompanyRequest.JoinDTO reqDTO){
//        //회원가입
//        return companyRepository.save(reqDTO.toEntity());
//
//    }

    // 회원가입
    @Transactional
    public Company joinAndLogin(CompanyRequest.JoinDTO reqDTO) {

        //회원가입
        Company joinUser = companyRepository.save(reqDTO.toEntity());

        //로그인
        Optional<Company> companyOP = Optional.ofNullable(companyRepository.findByEmail(joinUser.getEmail())
                .orElseThrow(() -> new Exception404("해당 이메일을 찾을 수 없습니다")));

        //로그인
        return new Company(joinUser);

    }

}

package com.example.final_project.company;

import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project._core.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;


    //로그인
    @Transactional
    public String login(CompanyRequest.LoginDTO reqDTO){
        //1. 아이디 체크
        Company sessionUser = companyRepository.findByIdAndPassword(reqDTO.getEmail(),reqDTO.getPassword())
                .orElseThrow( () -> new Exception404("아이디 및 패스워드가 일치하지않습니다"));

        String jwt = JwtUtil.companyCreate(sessionUser);

        return jwt;
    }
}

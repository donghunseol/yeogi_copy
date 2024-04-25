package com.example.final_project.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;


    //로그인
//    @Transactional
//    public String login(CompanyRequest.LoginDTO reqDTO){
//        //1. 아이디 체크
//        Company sessionUser = companyRepository.findByIdAndPassword(reqDTO.getUsername(),reqDTO.getPassword())
//    }
}

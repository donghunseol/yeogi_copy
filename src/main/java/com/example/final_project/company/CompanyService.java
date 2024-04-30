package com.example.final_project.company;

import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.stay.Stay;
import com.example.final_project.stay.StayRepository;
import com.example.final_project.stay_image.StayImage;
import com.example.final_project.stay_image.StayImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final StayRepository stayRepository;
    private final StayImageRepository stayImageRepository;

    //로그인
    @Transactional
    public String login(CompanyRequest.LoginDTO reqDTO){
        //1. 아이디 체크
        Company sessionUser = companyRepository.findByIdAndPassword(reqDTO.getEmail(),reqDTO.getPassword())
                .orElseThrow( () -> new Exception404("아이디 및 패스워드가 일치하지않습니다"));

        String jwt = JwtUtil.companyCreate(sessionUser);

        return jwt;
    }

    // [숙소 관리] 로그인한 기업이 등록한 숙소 조회
    public List<CompanyResponse.companyStayListDTO> companyStayList(Integer companyId){
        List<Stay> stayList = stayRepository.findByCompanyId(companyId);

        List<CompanyResponse.companyStayListDTO> respDTO = stayList.stream().map(stay -> {
            List<StayImage> stayImageList = stayImageRepository.findByStayId(stay.getId());

            return new CompanyResponse.companyStayListDTO(stay, stayImageList.getFirst());
        }).collect(Collectors.toList());

        return respDTO;
    }
}

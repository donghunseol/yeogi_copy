package com.example.final_project.company;

import com.example.final_project._core.errors.exception.Exception400;
import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.user.SessionUser;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRequest;
import com.example.final_project.stay.Stay;
import com.example.final_project.stay.StayRepository;
import com.example.final_project.stay_image.StayImage;
import com.example.final_project.stay_image.StayImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
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
    public Company login(CompanyRequest.LoginDTO reqDTO){
        //1. 아이디 체크
        Company sessionUser = companyRepository.findByIdAndPassword(reqDTO.getEmail(),reqDTO.getPassword())
                .orElseThrow( () -> new Exception404("아이디 및 패스워드가 일치하지않습니다"));

//        String jwt = JwtUtil.companyCreate(sessionUser);
//        return jwt;

        return sessionUser;
    }

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

    // [숙소 관리] 로그인한 기업이 등록한 숙소 조회
    public List<CompanyResponse.companyStayListDTO> companyStayList(Integer companyId){
        List<Stay> stayList = stayRepository.findByCompanyId(companyId);

        List<CompanyResponse.companyStayListDTO> respDTO = stayList.stream().map(stay -> {
            List<StayImage> stayImageList = stayImageRepository.findByStayId(stay.getId());

            return new CompanyResponse.companyStayListDTO(stay, stayImageList.getFirst());
        }).collect(Collectors.toList());

        return respDTO;
    }

    // 회원수정
    @Transactional
    public Company updateCompany(Integer companyId,CompanyRequest.UpdateDTO reqDTO){
        // 인증처리
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new Exception400("로그인이 필요한 서비스 입니다."));
        System.out.println("결과 값 =====================================" +reqDTO);
        // 수정
        company.updateCompany(reqDTO);

        return company;
    }


//    // [숙소 관리 - 숙소 상세보기] 로그인한 기업이 등록한 특정 숙소 상세보기
//    public List<CompanyResponse.>

}

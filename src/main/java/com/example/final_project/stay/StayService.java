package com.example.final_project.stay;

import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception403;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.company.Company;
import com.example.final_project.company.CompanyRepository;
import com.example.final_project.company.SessionCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StayService {
    private final StayRepository stayRepository;
    private final CompanyRepository companyRepository;

    //숙소등록
    @Transactional
    public StayResponse.Save register(StayRequest.SaveDTO reqDTO, SessionCompany sessionCompany) {
        //1. 인증처리
        Company company = companyRepository.findById(sessionCompany.getId()).orElseThrow(
                () -> new Exception404("해당 기업을 찾을 수 없습니다")
        );
        //2. 권한처리
        if (company.getId() != sessionCompany.getId()) {
            throw new Exception401("숙소를 등록할 권한이 없습니다.");
        }

        Stay stay = stayRepository.save(reqDTO.toEntity(company));

        return new StayResponse.Save(stay, stay.getOptions());
    }

    //숙소 수정폼
    @Transactional
    public StayResponse.UpdateForm updateForm(Integer stayId) {

        Stay stay = stayRepository.findByStayId(stayId)
                .orElseThrow(() -> new Exception404("해당 숙소를 찾을 수 없습니다."));

        return new StayResponse.UpdateForm(stay, stay.getOptions());
    }

    //숙소 수정
    @Transactional
    public StayResponse.Update update(Integer stayId, SessionCompany sessionCompany, StayRequest.UpdateDTO reqDTO) {
        //1. 인증처리
        Stay stay = stayRepository.findByStayId(stayId)
                .orElseThrow(() -> new Exception404("해당 숙소를 찾을 수 없습니다."));
        //2. 권한처리
        if (stay.getCompany().getId() != sessionCompany.getId()) {
            throw new Exception403("해당 숙소를 수정할 권한이 없습니다.");
        }
        //3. 수정
        stay.updateStay(reqDTO);

        System.out.println("결과값 ================== " + stay.getCreatedAt());

        return new StayResponse.Update(stay);
    }

    //숙소 삭제
    @Transactional
    public StayResponse.Delete delete(Integer stayId, SessionCompany sessionCompany, StayRequest.DeleteDTO reqDTO) {
        //1. 인증처리

        if (sessionCompany.getId() == null) {
            throw new Exception401("로그인이 필요한 서비스입니다.");
        }

        Stay stay = stayRepository.findByStayId(stayId)
                .orElseThrow(() -> new Exception404("해당 숙소를 찾을 수 없습니다."));

        //2. 권한처리
        Company company = companyRepository.findByStayId(stayId)
                .orElseThrow(() -> new Exception404("해당 기업을 찾을 수 업습니다."));

        if (sessionCompany.getId() != company.getId()) {
            throw new Exception403("삭제할 권한이 없습니다");
        }

        //3. 삭제(state 업데이트)
        stay.deleteStay(reqDTO);

        return new StayResponse.Delete(stay);
    }

    // 숙소 검색 기능
    public List<StayResponse.SearchListDTO> getSearchStayList(StayRequest.SearchDTO reqDTO) {
        List<StayResponse.SearchListDTO> resultList;

        resultList = stayRepository.findBySearchStay(reqDTO.getName(), reqDTO.getAddress(), reqDTO.getPrice()).stream()
                .map(StayResponse.SearchListDTO::new)
                .toList();

        return resultList;
    }
}

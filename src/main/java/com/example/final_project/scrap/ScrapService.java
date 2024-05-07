package com.example.final_project.scrap;

import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.company.SessionCompany;
import com.example.final_project.stay.Stay;
import com.example.final_project.stay.StayRepository;
import com.example.final_project.user.SessionUser;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Service
public class ScrapService {

    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final StayRepository stayRepository;


    // 찜 등록
    @Transactional
    public void register(ScrapRequest.ScrapRequestDTO reqDTO, SessionUser sessionUser){

        //1. 인증처리
        if (sessionUser == null){
            new Exception401("로그인이 필요한 서비스입니다.");
        }

        User user = userRepository.findById(reqDTO.getUserId())
                .orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다."));

        Stay stay = stayRepository.findById(reqDTO.getStayId())
                .orElseThrow(() -> new Exception404("해당 숙소를 찾을 수 없습니다"));

        //2. 이미 좋아요가 되어있으면 에러 반환
        if (scrapRepository.findByuserIdWithStayId(user.getId(),stay.getId()).isPresent()){
            throw new Exception404("이미 숙소가 찜 되어있습니다");
        }
        Scrap scrap = reqDTO.toEntity(stay,user);

        scrapRepository.save(scrap);

    }

    // 찜 삭제
    @Transactional
    public void delete(ScrapRequest.ScrapRequestDTO reqDTO, SessionUser sessionUser){
        //1. 인증처리
        if (sessionUser == null){
            new Exception401("로그인이 필요한 서비스입니다.");
        }
        User user = userRepository.findById(reqDTO.getUserId())
                .orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다."));

        Stay stay = stayRepository.findById(reqDTO.getStayId())
                .orElseThrow(() -> new Exception404("해당 숙소를 찾을 수 없습니다"));

        Scrap scrap = scrapRepository.findByuserIdWithStayId(user.getId(),stay.getId())
                .orElseThrow(() -> new Exception404("해당 찜목록 를 찾을 수 없습니다"));

        scrapRepository.delete(scrap);
    }
}

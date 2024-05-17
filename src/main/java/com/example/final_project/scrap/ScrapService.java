package com.example.final_project.scrap;

import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.stay.Stay;
import com.example.final_project.stay.StayRepository;
import com.example.final_project.stay_image.StayImage;
import com.example.final_project.stay_image.StayImageRepository;
import com.example.final_project.user.SessionUser;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScrapService {

    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final StayRepository stayRepository;
    private final StayImageRepository stayImageRepository;


    // 찜 등록
    @Transactional
    public ScrapResponse.Save register(Integer stayId, SessionUser sessionUser) {

        //1. 인증처리
        if (sessionUser == null) {
            throw new Exception401("로그인이 필요한 서비스입니다.");
        }

        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다."));

        Stay stay = stayRepository.findById(stayId)
                .orElseThrow(() -> new Exception404("해당 숙소를 찾을 수 없습니다"));

        //2. 이미 좋아요가 되어있으면 에러 반환
        if (scrapRepository.findByUserIdWithStayId(user.getId(), stay.getId()).isPresent()) {
            throw new Exception404("이미 숙소가 찜 되어있습니다");
        }

        Scrap scrap = Scrap.builder()
                .stay(stay)
                .user(user)
                .build();

        scrapRepository.save(scrap);

        return new ScrapResponse.Save(scrap);
    }


    //찜 삭제
    @Transactional
    public void delete(Integer stayId, SessionUser sessionUser) {
        //1. 인증처리
        if (sessionUser == null) {
            throw new Exception401("로그인이 필요한 서비스입니다.");
        }
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다."));

        Stay stay = stayRepository.findById(stayId)
                .orElseThrow(() -> new Exception404("해당 숙소를 찾을 수 없습니다"));

        Scrap scrap = scrapRepository.findByUserIdWithStayId(user.getId(), stay.getId())
                .orElseThrow(() -> new Exception404("해당 찜목록 를 찾을 수 없습니다"));

        scrapRepository.delete(scrap);
    }

    // 찜 목록
    public List<ScrapResponse.ScrapListDTO> myScrapList(SessionUser sessionUser) {
        //1. 인증처리
        if (sessionUser == null) {
            throw new Exception401("로그인이 필요한 서비스입니다.");
        }
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다."));
        List<Scrap> scrapList = scrapRepository.findByUserId(user.getId());
        return scrapList.stream().map(scrap -> {
            StayImage stayImage = stayImageRepository.findByStayId(scrap.getStay().getId()).getFirst();
            return new ScrapResponse.ScrapListDTO(scrap, scrap.getStay(), stayImage);
        }).collect(Collectors.toList());
    }

}

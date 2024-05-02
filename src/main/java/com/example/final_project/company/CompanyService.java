package com.example.final_project.company;

import com.example.final_project._core.errors.exception.Exception400;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.pay.Pay;
import com.example.final_project.pay.PayRepository;
import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.room.Room;
import com.example.final_project.room.RoomRepository;
import com.example.final_project.stay.Stay;
import com.example.final_project.stay.StayRepository;
import com.example.final_project.stay_image.StayImage;
import com.example.final_project.stay_image.StayImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;


@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final StayRepository stayRepository;
    private final StayImageRepository stayImageRepository;
    private final RoomRepository roomRepository;
    private final PayRepository payRepository;


    //JWT - 로그인
    @Transactional
    public String jwtlogin(CompanyRequest.LoginDTO reqDTO){
        //1. 아이디 체크
        Company sessionUser = companyRepository.findByIdAndPassword(reqDTO.getEmail(),reqDTO.getPassword())
                .orElseThrow( () -> new Exception404("아이디 및 패스워드가 일치하지않습니다"));

        String jwt = JwtUtil.companyCreate(sessionUser);

        return jwt;

    }

    //로그인
    @Transactional
    public SessionCompany login(CompanyRequest.LoginDTO reqDTO){
        //1. 아이디 체크
        Company sessionUser = companyRepository.findByIdAndPassword(reqDTO.getEmail(),reqDTO.getPassword())
                .orElseThrow( () -> new Exception404("아이디 및 패스워드가 일치하지않습니다"));

        return new SessionCompany(sessionUser);
    }

    // 회원가입
    @Transactional
    public SessionCompany joinAndLogin(CompanyRequest.JoinDTO reqDTO) {

        Optional<Company> companyOP = Optional.ofNullable(companyRepository.findByEmail(reqDTO.getEmail())
                .orElseThrow(() -> new Exception404("해당 이메일을 찾을 수 없습니다")));

        if (companyOP.isPresent()){
            throw new Exception400("중복된 이메일입니다");
        }

        //회원가입
        Company joinUser = companyRepository.save(reqDTO.toEntity());

        //로그인
        return new SessionCompany(joinUser);

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
    public SessionCompany updateCompany(Integer companyId,CompanyRequest.UpdateDTO reqDTO){
        // 인증처리
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new Exception400("로그인이 필요한 서비스 입니다."));
        System.out.println("결과 값 =====================================" +reqDTO);
        // 수정
        company.updateCompany(reqDTO);

        return new SessionCompany(company);
    }


    // [숙소 관리 - 숙소 상세보기] 로그인한 기업이 등록한 특정 숙소 상세보기
    public List<CompanyResponse.companyStayDetailDTO> companyStayDetailList(Integer stayId){
        List<CompanyResponse.companyStayDetailDTO> respDTO = roomRepository.findAndCountByStayId(stayId);
        return respDTO;
    }


    // [숙소 관리 - 숙소 상세보기 - 객실 상세보기] 로그인한 기업이 등록한 특정 숙소의 객실 상세보기
    public List<CompanyResponse.companyRoomDetailDTO> companyRoomDetail(Integer stayId, String tier){
        List<Room> roomList = roomRepository.findByStayIdAndTier(stayId, tier);
        List<CompanyResponse.companyRoomDetailDTO> respDTO = roomList.stream().map(room -> {
            Pay pay = null;
            if(payRepository.findByRoomId(room.getId(), LocalDate.of(2023,12,31)) != null){
                pay = payRepository.findByRoomId(room.getId(), LocalDate.of(2023,12,31));
            }else{

            }

            return new CompanyResponse.companyRoomDetailDTO(room, pay);
        }).collect(Collectors.toList());
        return respDTO;
    }
}

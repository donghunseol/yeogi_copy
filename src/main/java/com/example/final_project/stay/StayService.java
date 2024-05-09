package com.example.final_project.stay;

import com.example.final_project._core.enums.RoomEnum;
import com.example.final_project._core.errors.exception.Exception400;
import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception403;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.company.Company;
import com.example.final_project.company.CompanyRepository;
import com.example.final_project.company.SessionCompany;
import com.example.final_project.option.Option;
import com.example.final_project.option.OptionRepository;
import com.example.final_project.review.Review;
import com.example.final_project.review.ReviewRepository;
import com.example.final_project.room.Room;
import com.example.final_project.room.RoomRepository;
import com.example.final_project.room_information.RoomInformation;
import com.example.final_project.room_information.RoomInformationRepository;
import com.example.final_project.stay_image.StayImage;
import com.example.final_project.stay_image.StayImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StayService {
    private final StayRepository stayRepository;
    private final CompanyRepository companyRepository;
    private final OptionRepository optionRepository;
    private final StayImageRepository stayImageRepository;
    private final ReviewRepository reviewRepository;
    private final RoomRepository roomRepository;
    private final RoomInformationRepository roomInformationRepository;


    @Transactional
    public void register(StayRequest.SaveDTO reqDTO, SessionCompany sessionUser) {

        //1. 인증처리
        Optional<Company> companyOP = companyRepository.findById(sessionUser.getId());
        Company company = companyOP.orElseThrow(() -> new Exception404("해당 기업을 찾을 수 없습니다"));

        //2. 권한처리
        if (!company.getId().equals(sessionUser.getId())) {
            throw new Exception401("숙소를 등록할 권한이 없습니다.");
        }

        Stay stay = stayRepository.save(reqDTO.toEntity(company));

        // 옵션 등록
        if (reqDTO.getOptions() != null && !reqDTO.getOptions().isEmpty()) {
            List<Option> options = reqDTO.getOptions().stream()
                    .map(optionName -> {
                        return new Option(stay, optionName);
                    })
                    .toList();

            optionRepository.saveAll(options);
        }

        //사진 등록
        StayImage stayImage = new StayImage(stay);
        stayImageRepository.save(stayImage);
    }

    //숙소 등록폼
    @Transactional
    public StayResponse.UpdateFormDTO updateForm(Integer stayId, SessionCompany sessionUser) {
        // 1. 인증 처리
        if (sessionUser == null) {
            throw new Exception400("로그인이 필요한 서비스입니다");
        }

        Stay stay = stayRepository.findByStayId(stayId)
                .orElseThrow(() -> new Exception404("해당 숙소를 찾을 수 없습니다."));

        Company company = companyRepository.findByStayId(stay.getId())
                .orElseThrow(() -> new Exception404("해당 기업을 찾을 수 없습니다"));

        // 2. 권한 처리
        if (!sessionUser.getId().equals(company.getId())) {
            throw new Exception401("정보를 수정할 권한이 없습니다");
        }

        List<Option> options = optionRepository.findByStayId(stay.getId());

        // Option을 OptionChekedDTO로 변환
        List<StayResponse.UpdateFormDTO.OptionChekedDTO> optionDTOs = new ArrayList<>();
        optionDTOs.add(new StayResponse.UpdateFormDTO.OptionChekedDTO(options));
        return new StayResponse.UpdateFormDTO(stay, optionDTOs);

    }

    //숙소 수정
    @Transactional
    public void update(Integer stayId, SessionCompany sessionCompany, StayRequest.UpdateDTO reqDTO) {

        //1. 인증처리
        Stay stay = stayRepository.findById(stayId)
                .orElseThrow(() -> new Exception404("해당 숙소를 찾을 수 없습니다."));

        //2. 권한처리
        if (stay.getCompany().getId() != sessionCompany.getId()) {
            throw new Exception403("해당 숙소를 수정할 권한이 없습니다.");
        }

        //3. 숙소정보 저장
        stay.updateStay(reqDTO);

        List<Option> beforeOptions = optionRepository.findByStayId(stayId);

        //4. 옵션 삭제
        beforeOptions.clear();
        optionRepository.deleteBystayId(stayId);

        //5. 옵션 저장
        if (reqDTO.getOptions() != null && !reqDTO.getOptions().isEmpty()) {
            List<Option> options = reqDTO.getOptions().stream()
                    .map(optionName -> {
                        return new Option(stay, optionName);
                    })
                    .toList();

            optionRepository.saveAll(options);

        }

    }

    //숙소 삭제
    @Transactional
    public StayResponse.Delete delete(Integer stayId, SessionCompany sessionCompany) {
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
        stay.deleteStay(stay.getState());

        return new StayResponse.Delete(stay);
    }

//    // 숙소 검색 기능 (이름, 지역, 날짜, 가격, 인원 수, 예약 날짜 별 검색) // request 방식
//    public List<StayResponse.SearchListDTO> getSearchStayList(StayRequest.SearchDTO reqDTO) {
//        List<StayResponse.SearchListDTO> resultList;
//
//        resultList = stayRepository.findBySearchStay(reqDTO.getName(), reqDTO.getAddress(), reqDTO.getPrice(), reqDTO.getPerson(), reqDTO.getCheckInDate(), reqDTO.getCheckOutDate()).stream()
//                .map(StayResponse.SearchListDTO::new)
//                .toList();
//
//        return resultList;
//    }

    // 숙소 검색 기능 (이름, 지역, 날짜, 가격, 인원 수 검색)
    public List<StayResponse.SearchListDTO> getSearchStayList(
            String stayName,
            String stayAddress,
            Integer roomPrice,
            Integer person
    ) {
        List<Stay> stayList = stayRepository.findBySearchStay(stayName, stayAddress, roomPrice, person);
        return stayList.stream()
                .map(StayResponse.SearchListDTO::new)
                .toList();
    }

    @Transactional
    public StayResponse.AllList findAllStayWithCategory(){

        // TODO: 이벤트 추가

        // 국내 숙소 찾기
        List<Stay> domesticStays = stayRepository.findAll().stream()
                .filter(stay -> !stay.getCategory().equals("해외"))
                .collect(Collectors.toList());

        System.out.println("국내결과===========================================" + domesticStays.size());

        List<StayResponse.AllList.DomesticDTO> domesticDTOs = domesticStays.stream()
                .map(stay -> {
                    StayImage domesticStayImage = stayImageRepository.findByStayId(stay.getId()).stream().findFirst().orElse(null);
                    String imageName = (domesticStayImage != null) ? domesticStayImage.getName() : null;
                    String imagePath = (domesticStayImage != null) ? domesticStayImage.getPath() : null;
                    return new StayResponse.AllList.DomesticDTO(stay, imageName, imagePath);
                })
                .collect(Collectors.toList());


        // 해외 숙소 찾기
        List<Stay> overseaStays = stayRepository.findAll().stream()
                .filter(stay -> stay.getCategory().equals("해외"))
                .collect(Collectors.toList());

        System.out.println("해외결과===========================================" +overseaStays.size());


        List<StayResponse.AllList.OverseaDTO> overseaDTOs = overseaStays.stream()
                .map(stay -> {
                    StayImage overseaStayImage = stayImageRepository.findByStayId(stay.getId()).stream().findFirst().orElse(null);
                    return new StayResponse.AllList.OverseaDTO(stay, overseaStayImage);
                })
                .collect(Collectors.toList());

        // 특가 숙소 찾기
        List<Stay> specialPriceStays = stayRepository.findAll().stream()
                .filter(stay -> stay.getRooms().stream().anyMatch(room -> room.getSpecialState() == RoomEnum.APPLIED))
                .collect(Collectors.toList());

        System.out.println("특가결과===========================================" +specialPriceStays.size());


        List<StayResponse.AllList.SpecialPriceDTO> specialPriceDTOs = specialPriceStays.stream()
                .map(stay -> {
                    StayImage specialPriceStayImage = stayImageRepository.findByStayId(stay.getId()).stream().findFirst().orElse(null);
                    return new StayResponse.AllList.SpecialPriceDTO(stay, specialPriceStayImage);
                })
                .collect(Collectors.toList());

        return new StayResponse.AllList(specialPriceDTOs, domesticDTOs, overseaDTOs);
    }

    @Transactional
    public StayResponse.StayDetail findStayDetail(Integer stayId) {
        // section1 (숙소 이름, 찜 여부, 숙소 이미지, 숙소 리뷰, 숙소 편의시설)
        Stay stay = stayRepository.findByStayId(1)
                .orElseThrow(() -> new Exception404("존재하지 않는 숙소입니다.")); // 숙소
        StayResponse.StayDetail.StayContentsDTO.StayDTO stayDTO = new StayResponse.StayDetail.StayContentsDTO.StayDTO(stay);


        // TODO: 찜 여부 불러오기
        List<StayImage> stayImageList = stayImageRepository.findByStayId(stayId); // 숙소 이미지
        List<StayResponse.StayDetail.StayContentsDTO.StayImageDTO> stayImageDTOS = stayImageList.stream().map(StayResponse.StayDetail.StayContentsDTO.StayImageDTO::new).toList();


        List<Review> reviewList = reviewRepository.findNoParentReviewByStayIdWithDetails(stayId); // 숙소 리뷰
        List<StayResponse.StayDetail.StayContentsDTO.ReviewDTO> reviewDTOS = reviewList.stream().map(StayResponse.StayDetail.StayContentsDTO.ReviewDTO::new).toList();

        List<Option> optionList = optionRepository.findByStayId(stayId); // 숙소 편의시설
        List<StayResponse.StayDetail.StayContentsDTO.OptionDTO> optionDTOS = optionList.stream().map(StayResponse.StayDetail.StayContentsDTO.OptionDTO::new).collect(Collectors.toList());

        StayResponse.StayDetail.StayContentsDTO stayContentsDTO = new StayResponse.StayDetail.StayContentsDTO(stayDTO, stayImageDTOS, reviewDTOS, optionDTOS);

        // section2 (객실 리스트)
        List<Room> roomList = roomRepository.findByStayId(stayId);
        List<StayResponse.StayDetail.RoomContentsDTO> roomContentsDTOS = roomList.stream().map(room -> {
            RoomInformation roomInformation = roomInformationRepository.findByRoomId(room.getId());
            return new StayResponse.StayDetail.RoomContentsDTO(room, roomInformation);
        }).collect(Collectors.toList());

        // section3 (숙소 소개, 이용 정보, 취소 및 환불 규정) -> 이건 고정값

        return new StayResponse.StayDetail(stayContentsDTO, roomContentsDTOS);
    }








}

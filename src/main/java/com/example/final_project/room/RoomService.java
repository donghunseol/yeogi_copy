package com.example.final_project.room;

import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.company.CompanyRepository;
import com.example.final_project.company.SessionCompany;
import com.example.final_project.room_information.RoomInformation;
import com.example.final_project.room_information.RoomInformationRepository;
import com.example.final_project.stay.Stay;
import com.example.final_project.stay.StayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomsRepository;
    private final CompanyRepository companyRepository;
    private final StayRepository stayRepository;
    private final RoomInformationRepository roomInformationRepository;


    //객실 등록
    @Transactional
    public RoomResponse.Save register(Integer stayId, SessionCompany sessionUser, RoomRequest.SaveDTO reqDTO){
        //1. 인증처리
        if (sessionUser  == null){
            new Exception401("로그인이 필요한 서비스입니다");
        }

        //2. 권한처리
        Stay stay =stayRepository.findByStayId(stayId)
                .orElseThrow(() -> new Exception404("해당 기업이 없습니다"));

        //객실 등록
        Room room = roomsRepository.save(reqDTO.toEntity(stay));
        RoomInformation roomInformation = roomInformationRepository.save(reqDTO.toEntity(room));
        roomsRepository.save(room);
        roomInformationRepository.save(roomInformation);

        return new RoomResponse.Save(room,roomInformation);
    }


    //객실 디테일(API)
    @Transactional
    public RoomResponse.Detail detail(Integer roomId){

        Room room = roomsRepository.findById(roomId)
                .orElseThrow(() -> new Exception404("해당 객실을 찾을 수 없습니다"));

        RoomInformation roomInformation = roomInformationRepository.findByRoomId(roomId);

        RoomResponse.Detail.RoomInfoDTO roomInfoDTO = new RoomResponse.Detail.RoomInfoDTO(roomInformation);

        return new RoomResponse.Detail(room,roomInfoDTO);
    }

}

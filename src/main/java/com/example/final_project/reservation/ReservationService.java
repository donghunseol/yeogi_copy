package com.example.final_project.reservation;

import com.example.final_project._core.enums.PayEnum;
import com.example.final_project._core.errors.exception.Exception400;
import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.company.CompanyResponse;
import com.example.final_project.company.SessionCompany;
import com.example.final_project.pay.Pay;
import com.example.final_project.pay.PayRepository;
import com.example.final_project.room.Room;
import com.example.final_project.room.RoomRepository;
import com.example.final_project.user.SessionUser;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final PayRepository payRepository;

    // 예약 하기
    @Transactional
    public ReservationResponse.DTO makeReservation(ReservationRequest.DTO reqDTO, SessionUser sessionUser, Integer roomId) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 방입니다"));

        // 겹치는 예약 날짜 확인
        System.out.println("예약 입실 일자 : " + reqDTO.getCheckInDate());
        System.out.println("예약 퇴실 일자 : " + reqDTO.getCheckOutDate());
        Optional<List<Reservation>> reservationsOP = reservationRepository.findReservationsByDateRangeAndRoomId(roomId, reqDTO.getCheckInDate(), reqDTO.getCheckOutDate());
        System.out.println(reservationsOP.get().size());

        if (reservationsOP.get().size() != 0) {
            throw new Exception400("이미 예약된 날짜 입니다");
        }

        Reservation reservation = reservationRepository.save(reqDTO.toEntity(user, room));

        // 예약 시 결제 안된 상태로 결제 데이터 생성
        Pay pay = Pay.builder()
                .reservation(reservation)
                .amount(0)
                .way("No payment")
                .state(PayEnum.PROCESSING)
                .createdAt(LocalDateTime.now())
                .build();

        payRepository.save(pay);

        return new ReservationResponse.DTO(reservation);
    }

    // 예약 수정 하기
    @Transactional
    public ReservationResponse.DTO modifyReservation(ReservationRequest.UpdateDTO reqDTO, SessionUser sessionUser, Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 예약입니다"));

        // 예약한 유저와 로그인한 유저가 같은 인원인지 확인하는 권한 체크
        if (sessionUser.getId() != reservation.getUser().getId()) {
            throw new Exception401("예약을 수정할 권한이 없습니다");
        }

        // 수정
        reservation.updateReservation(reqDTO);

        // 수정 후 뿌려줄 데이터를 리턴
        return new ReservationResponse.DTO(reservation);
    }

    // 예약 내역 조회 (목록)
    public List<ReservationResponse.ListDTO> userReservationList(SessionUser sessionUser) {
        List<Reservation> reservationList = reservationRepository.findByUserIdWithRoomAndStay(sessionUser.getId());

        List<ReservationResponse.ListDTO> respDTO = reservationList.stream().map(reservation -> {
            return new ReservationResponse.ListDTO(reservation, reservation.getRoom());
        }).collect(Collectors.toList());

        return respDTO;
    }


    // 예약 내역 조회 (상세보기)
    public ReservationResponse.DetailDTO reservationDetail(SessionUser sessionUser, Integer reservationId) {
        Reservation reservation = reservationRepository.findByReservationIdWithRoomAndStay(reservationId);
        if (sessionUser.getId() != reservation.getUser().getId()) {
            throw new Exception401("예약내역을 열람할 권한이 없습니다");
        }
        Optional<Pay> payOP = payRepository.findByReservationId(reservation.getId());
        Pay pay = null;
        if (payOP.isPresent()) pay = payOP.get();
        return new ReservationResponse.DetailDTO(reservation, reservation.getRoom(), pay);
    }

    // 기업의 예약 현황 확인
    public List<CompanyResponse.ReservationListDTO> compReservationList(SessionCompany sessionCompany) {
        List<Reservation> reservationList = reservationRepository.findByCompanyIdWithRoomAndStay(sessionCompany.getId());
        return reservationList.stream().map(reservation -> {
            Optional<Pay> payOP = payRepository.findByReservationId(reservation.getId());
            Pay pay = null;
            if (payOP.isPresent()) pay = payOP.get();
            return new CompanyResponse.ReservationListDTO(reservation, reservation.getRoom(), pay);
        }).collect(Collectors.toList());
    }
}

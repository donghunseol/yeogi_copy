package com.example.final_project.reservation;

import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.pay.PayRepository;
import com.example.final_project.room.Room;
import com.example.final_project.room.RoomRepository;
import com.example.final_project.user.SessionUser;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final PayRepository payRepository;

    // 예약 하기
    @Transactional
    public ReservationResponse.DTO save(ReservationRequest.DTO reqDTO, SessionUser sessionUser, Integer roomId) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 방입니다"));

        Reservation reservation = reservationRepository.save(reqDTO.toEntity(user, room));
        return new ReservationResponse.DTO(reservation);
    }

    // 예약 수정 하기
    @Transactional
    public ReservationResponse.DTO update(ReservationRequest.UpdateDTO reqDTO, SessionUser sessionUser, Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 예약입니다"));

        // 예약한 유저와 로그인한 유저가 같은 인원인지 확인하는 권한 체크
        if (sessionUser.getId() != reservation.getUser().getId()) {
            throw new Exception401("예약을 수정할 권한이 없습니다");
        }

        reservation.setReservationName(reqDTO.getReservationName());
        reservation.setReservationTel(reqDTO.getReservationTel());

        // 수정 후 뿌려줄 데이터를 리턴
        return new ReservationResponse.DTO(reservation);
    } // 더티 체킹
}

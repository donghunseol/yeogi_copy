package com.example.final_project.reservation;

import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.room.Room;
import com.example.final_project.room.RoomRepository;
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
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

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

    // 예약 내역 조회(목록)
    public List<ReservationResponse.ListDTO> reservationList(SessionUser sessionUser){
        List<Reservation> reservationList = reservationRepository.findByUserIdWithRoomAndStay(sessionUser.getId());

        List<ReservationResponse.ListDTO> result = reservationList.stream().map(reservation -> {
            return new ReservationResponse.ListDTO(reservation, reservation.getRoom());
        }).collect(Collectors.toList());

        return result;
    }
}

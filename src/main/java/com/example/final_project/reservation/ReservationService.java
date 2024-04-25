package com.example.final_project.reservation;

import com.example.final_project._core.errors.exception.Exception404;
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
    private final UserRepository userRepository;

    // 예약 하기
    @Transactional
    public void save(ReservationRequest.SaveDTO reqDTO, SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));
        // Reservation reservation = reservationRepository.save(reqDTO)
    }
}

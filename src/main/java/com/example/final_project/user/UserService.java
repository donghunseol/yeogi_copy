package com.example.final_project.user;

import com.example.final_project._core.errors.exception.Exception400;
import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.reservation.Reservation;
import com.example.final_project.reservation.ReservationRepository;
import com.example.final_project.stay.StayRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    // 로그인 기능
    public String login(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

        String jwt = JwtUtil.userCreate(user);
        JwtUtil.userVerify(jwt);

        return jwt;
    }

    // 회원 가입
    @Transactional
    public String joinAndLogin(UserRequest.JoinDTO reqDTO) {
        Optional<User> userOP = userRepository.findByEmail(reqDTO.getEmail());

        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저네임입니다");
        }

        // 회원 가입
        User joinUser = userRepository.save(reqDTO.toEntity());

        // 로그인
        String jwt = JwtUtil.userCreate(joinUser);
        JwtUtil.userVerify(jwt);

        return jwt;
    }

    // 회원 정보 수정
    @Transactional
    public SessionUser update(SessionUser sessionUser, UserRequest.UpdateDTO reqDTO, Integer userId) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재 하지 않는 유저입니다"));

        // 권한 체크
        if (user.getId() != userId) {
            throw new Exception401("유저 정보를 수정할 권한이 없습니다");
        }

        // 수정
        user.updateUser(reqDTO);

        return new SessionUser(user);
    }

    public UserResponse.LoginDTO loginByDTO(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

        return new UserResponse.LoginDTO(user);
    }

    public UserResponse.JoinDTO joinByDTO(UserRequest.JoinDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

        return new UserResponse.JoinDTO(user);
    }

    public List<UserResponse.Notifications> notifications(SessionUser sessionUser){
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재 하지 않는 유저입니다"));
        List<Reservation> reservationList = reservationRepository.findByUserId(user.getId());
        return reservationList.stream().map(UserResponse.Notifications::new).collect(Collectors.toList());
    }

}

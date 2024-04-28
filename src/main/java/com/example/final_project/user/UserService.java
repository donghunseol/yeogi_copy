package com.example.final_project.user;

import com.example.final_project._core.errors.exception.Exception400;
import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // 로그인 기능
    public SessionUser login(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

        return new SessionUser(user);
    }

    // 회원 가입
    @Transactional
    public SessionUser joinAndLogin(UserRequest.JoinDTO reqDTO) {
        Optional<User> userOP = userRepository.findByEmail(reqDTO.getEmail());

        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저네임입니다");
        }

        // 회원 가입
        User joinUser = userRepository.save(reqDTO.toEntity());

        // 로그인
        return new SessionUser(joinUser);
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
}

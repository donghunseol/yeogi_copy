package com.example.final_project.user;

import com.example.final_project._core.errors.exception.Exception400;
import com.example.final_project._core.errors.exception.Exception401;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // 로그인 기능
    public User login(UserRequest.LoginDTO reqDTO) {
        User SessionUser = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

        return SessionUser;
    }

    // 회원 가입
    public void join(UserRequest.JoinDTO reqDTO) {
        Optional<User> userOP = userRepository.findByEmail(reqDTO.getEmail());

        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저네임입니다");
        }

        userRepository.save(reqDTO.toEntity());
    }
}

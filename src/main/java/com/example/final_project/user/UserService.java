package com.example.final_project.user;

import com.example.final_project._core.errors.exception.Exception401;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User login(UserRequest.LoginDTO reqDTO){
        User SessionUser = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

        return SessionUser;
    }
}

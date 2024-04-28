package com.example.final_project.admin;

import com.example.final_project.user.User;
import com.example.final_project.user.UserRepository;
import com.example.final_project.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    // 모든 유저 정보 리스트
    public List<AdminResponse.userListDTO> adminUserList(){
        List<User> userList = userRepository.findAll();

        List<AdminResponse.userListDTO> respDTO = userList.stream().map(user -> {
            return  new AdminResponse.userListDTO(user);
        }).collect(Collectors.toList());

        return respDTO;
    }

}

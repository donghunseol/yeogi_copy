package com.example.final_project.company;

import com.example.final_project._core.utils.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CompanyRestController {

    private final CompanyService companyService;

    //로그인
   @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CompanyRequest.LoginDTO reqDTO){

        String jwt = companyService.login(reqDTO);

        return ResponseEntity.ok().header("Authorization", "Bearer " + jwt)
               .body(new ApiUtil<>(null));
    }
}

package com.example.final_project.company;

import com.example.final_project._core.utils.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CompanyRestController {

    private final CompanyService companyService;

    //로그인
   @PostMapping("/jwt-login")
    public ResponseEntity<?> login(@RequestBody CompanyRequest.LoginDTO reqDTO){

        String jwt = companyService.jwtlogin(reqDTO);

        return ResponseEntity.ok().header("Authorization", "Bearer " + jwt)
               .body(new ApiUtil<>(null));
    }

    @GetMapping("/company/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(String email) {
        Company user = companyService.findByEmail(email);
        if (user == null) {
            return new ApiUtil<>(true);
        } else {
            return new ApiUtil<>(false);
        }
    }
}



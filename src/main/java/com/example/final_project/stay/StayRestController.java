package com.example.final_project.stay;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.company.SessionCompany;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StayRestController {
    private final StayService stayService;
    private final HttpSession session;


    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody StayRequest.SaveDTO reqDTO){
        SessionCompany sessionCompany = (SessionCompany) session.getAttribute("sessionCompany");

        StayResponse.Save respDTO = stayService.register(reqDTO,sessionCompany);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

}

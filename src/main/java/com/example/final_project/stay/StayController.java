package com.example.final_project.stay;

import com.example.final_project._core.utils.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StayController {
    private final StayService stayService;
    private final HttpSession session;

    // 숙소 검색 기능 (이름, 지역, 날짜, 가격, 인원 수 별 검색)
    @GetMapping("/stay")
    public ResponseEntity<?> searchStay(@RequestBody StayRequest.SearchDTO reqDTO) {
        List<StayResponse.SearchListDTO> respDTO = stayService.getSearchStayList(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}

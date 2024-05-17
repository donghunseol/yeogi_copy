package com.example.final_project.scrap;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/scrap") // 주소 앞에 자동으로 붙는다.
@RestController
public class ScrapRestController {

    private final ScrapService scrapService;
    private final HttpSession session;

    // 스크랩 입력
    @PostMapping("/stay/{stayId}")
    public ResponseEntity<?> insert(@PathVariable Integer stayId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        scrapService.register(stayId, sessionUser);

        return ResponseEntity.ok().body(new ApiUtil<>("스크랩 등록 성공"));
    }

    // 스크랩 삭제
    @DeleteMapping("/stay/{stayId}")
    public ResponseEntity<?> delete(@PathVariable Integer stayId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        scrapService.delete(stayId, sessionUser);

        return ResponseEntity.ok().body(new ApiUtil<>("스크랩 취소 성공"));
    }

    @GetMapping("/my-scraps")
    public ResponseEntity<?> myScrapList() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ScrapResponse.ScrapListDTO> respDTO = scrapService.myScrapList(sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}

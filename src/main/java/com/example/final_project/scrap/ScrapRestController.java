package com.example.final_project.scrap;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/scrap")
@RestController
public class ScrapRestController {

    private final ScrapService scrapService;
    private final HttpSession session;

    // 좋아요 입력
    @PostMapping("/stay/{stayId}")
    public ResponseEntity<?> insert(@RequestBody ScrapRequest.ScrapRequestDTO reqDTO){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        scrapService.register(reqDTO,sessionUser);

        return ResponseEntity.ok().body(new ApiUtil<>("좋아요 성공"));

    }

    // 좋아요 삭제
    @DeleteMapping("/stay/{stayId}")
    public ResponseEntity<?> delete(@RequestBody ScrapRequest.ScrapRequestDTO reqDTO){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        scrapService.delete(reqDTO,sessionUser);

        return ResponseEntity.ok().body(new ApiUtil<>("좋아요 취소 성공"));
    }
}

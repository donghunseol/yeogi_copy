package com.example.final_project.review;

import com.example.final_project._core.utils.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewRestController {

    private final HttpSession session;
    private final ReviewService reviewService;


    // 댓글작성
    @PostMapping("/api/review/{stayId}")
    public ResponseEntity<?> insert(@PathVariable Integer stayId, @RequestBody ReviewRequest.ReviewRequestDTO reqDTO){

        ReviewResponse.Save respDTO = reviewService.insert(stayId,reqDTO);

        return ResponseEntity.ok()
                .body(new ApiUtil<>(respDTO));
    }

    // 댓글조회
    @GetMapping("/api/review/stay/{stayId}")
    public ResponseEntity<?> findByStayId(@PathVariable Integer stayId){

        List<ReviewResponse.Find> respDTO = reviewService.select(stayId);

        return ResponseEntity.ok()
                .body(new ApiUtil<>(respDTO));
    }

    // 댓글삭제
    @PutMapping("/api/review/{reviewId}")
    public ResponseEntity<?> delete(@PathVariable Integer reviewId){

        ReviewResponse.Delete respDTO = reviewService.delete(reviewId);

        return ResponseEntity.ok()
                .body(new ApiUtil<>(respDTO));
    }
}

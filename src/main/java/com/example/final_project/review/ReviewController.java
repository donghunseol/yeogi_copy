package com.example.final_project.review;

import com.example.final_project.company.Company;
import com.example.final_project.company.SessionCompany;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.SpinnerUI;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReviewController {
    private final ReviewService reviewService;
    private final HttpSession session;


    //리뷰 테이블
    @GetMapping("/review/{stayId}")
    public String reviewList(@PathVariable Integer stayId, HttpServletRequest request){
        SessionCompany company = (SessionCompany) session.getAttribute("sessionUser");
        List<ReviewResponse.Find> respDTO = reviewService.select(stayId,company);
        request.setAttribute("reviewList",respDTO);

        return "/company/review/main";
    }


    //리뷰 디테일
    @GetMapping("/review/detail/{reviewId}")
    public String reviewDetail(@PathVariable Integer reviewId , HttpServletRequest request){
        SessionCompany company = (SessionCompany) session.getAttribute("sessionUser");
        ReviewResponse.Detail respDTO = reviewService.detail(reviewId,company);
        request.setAttribute("review",respDTO);

        return "/company/review/detail";
    }


}

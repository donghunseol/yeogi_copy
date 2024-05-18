

package com.example.final_project.review;

import com.example.final_project.company.Company;
import com.example.final_project.company.SessionCompany;
import com.example.final_project.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.SpinnerUI;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReviewController {
    private final ReviewService reviewService;
    private final HttpSession session;


    // 해당 숙소의 리뷰(댓글) 목록
    @GetMapping("/reviews/{stayId}")
    public String reviewList(@PathVariable Integer stayId, HttpServletRequest request){
        SessionCompany sessionUser = (SessionCompany) session.getAttribute("sessionUser");
        List<ReviewResponse.Find> respDTO = reviewService.select(stayId,sessionUser);
        request.setAttribute("reviewCount", respDTO.size());
        request.setAttribute("reviewList",respDTO);

        return "/company/review/main";
    }

    //댓글 디테일
    @GetMapping("/reviews/detail/{reviewId}")
    public String reviewDetail(@PathVariable Integer reviewId , HttpServletRequest request){
        SessionCompany sessionUser = (SessionCompany) session.getAttribute("sessionUser");
        ReviewResponse.Detail respDTO = reviewService.detail(reviewId,sessionUser);
        request.setAttribute("review",respDTO);
        System.out.println(respDTO);
        return "/company/review/detail";
    }

    //댓글 작성
    @PostMapping("/review/write/{stayId}")
    public String reviewWrite(@PathVariable Integer stayId, ReviewRequest.ReviewRequestDTO reqDTO){
        Object sessionObject = session.getAttribute("sessionUser"); // 세션에서 사용자 또는 기업 정보 가져오기
        reviewService.insert(stayId,reqDTO,sessionObject);
        System.out.println(reqDTO);

        return "redirect:/reviews/"+stayId;
    }

    //댓글 신고 폼
    @GetMapping("/review/report-form/{reviewId}")
    public String reviewReportForm(@PathVariable Integer reviewId, HttpServletRequest request){
        SessionCompany sessionUser = (SessionCompany) session.getAttribute("sessionUser");
        ReviewResponse.ReportForm review = reviewService.reportForm(reviewId,sessionUser);
        request.setAttribute("reportReview",review);


        return "/company/review/report";
    }

    //댓글 신고
    @PostMapping("/review/report/{reviewId}")
    public String reviewReport(@PathVariable Integer reviewId,ReviewRequest.ReportRequestDTO reqDTO){
        SessionCompany sessionUser = (SessionCompany) session.getAttribute("sessionUser");

        reviewService.reportSave(reviewId,sessionUser,reqDTO);

        return "redirect:/reviews/detail/"+reviewId;
    }
}

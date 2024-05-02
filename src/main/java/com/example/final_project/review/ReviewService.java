package com.example.final_project.review;

import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.company.Company;
import com.example.final_project.company.CompanyRepository;
import com.example.final_project.company.SessionCompany;
import com.example.final_project.stay.Stay;
import com.example.final_project.stay.StayRepository;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StayRepository stayRepository;
    private final CompanyRepository companyRepository;

    //API 리뷰 작성 및 대댓글 작성
    @Transactional
    public ReviewResponse.Save insert(Integer stayId, ReviewRequest.ReviewRequestDTO reqDTO) {

        User user = userRepository.findById(reqDTO.getUserId())
                .orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다" + reqDTO.getUserId()));

        Stay stay = stayRepository.findById(stayId)
                .orElseThrow(() -> new Exception404("해당 숙소를 찾을 수 없습니다 : " + stayId));

        Review review = reqDTO.toEntity(user,stay);

        Review parentReview;

        if (reqDTO.getParentId() != null) {
            parentReview = reviewRepository.findById(reqDTO.getParentId())
                    .orElseThrow(() -> new Exception404("해당 리뷰아이디를 찾을 수 없습니다. : " + reqDTO.getParentId()));
            review.updateParent(parentReview);
        }

        review.updateWriter(user);
        review.updateBoard(stay);

        reviewRepository.save(review);

        return  new ReviewResponse.Save(stayId, new ReviewResponse.Save.UserDTO(user), review.getContent(), review.getScore());
    }

    // 댓글 조회
    @Transactional
    public List<ReviewResponse.Find> select(Integer stayId , SessionCompany sessionUser) {

        //인증 처리
        if (sessionUser == null){
            new Exception401("로그인이 필요한 서비스입니다.");
        }
        //권한 처리
        Stay stay = stayRepository.findById(stayId)
                .orElseThrow(() -> new Exception401("조회할 권한이 없습니다"));

        Company company = companyRepository.findByStayId(stay.getId())
                .orElseThrow(() -> new Exception404("해당 기없을 찾을 수 없습니다"));

        if (sessionUser.getId() != company.getId()){
            new Exception401("해당 기업의 리뷰를 조회 할 권한이 없습니다.");
        }

        // 리스트 조회
        List<Review> reviewList = reviewRepository.findAllByStayIdWithDetails(stayId);

        // ReviewResponse.Find 객체로 변환하여 반환
        Map<Integer, ReviewResponse.Find> reviewMap = new HashMap<>();
        List<ReviewResponse.Find> roots = new ArrayList<>();

        for (Review review : reviewList) {
            ReviewResponse.Find reviewFind = new ReviewResponse.Find(
                    review.getId(),
                    new ReviewResponse.Find.UserDTO(review.getWriter()),
                    review.getContent(),
                    review.getCreatedAt(),
                    review.getScore()
            );

            reviewMap.put(reviewFind.getId(), reviewFind);

            if (review.getParent() != null) {
                ReviewResponse.Find parentFind = reviewMap.get(review.getParent().getId());
                if (parentFind != null) {
                    parentFind.getChildren().add(reviewFind);
                }
            } else {
                roots.add(reviewFind);
            }
        }

        return roots;
    }

    //댓글 삭제
    @Transactional
    public ReviewResponse.Delete delete(Integer reviewId){

        // 리뷰찾기
        Review review = reviewRepository.findReviewByIdWithParent(reviewId);

        //삭제
        review.changeIsDeleted(review.getIsDelete());

        return new ReviewResponse.Delete(review.getIsDelete());
    }




}

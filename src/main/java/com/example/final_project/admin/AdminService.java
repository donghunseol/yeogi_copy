package com.example.final_project.admin;

import com.example.final_project._core.enums.CompanyEnum;
import com.example.final_project._core.enums.UserEnum;
import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.company.Company;
import com.example.final_project.company.CompanyRepository;
import com.example.final_project.pay.Pay;
import com.example.final_project.pay.PayRepository;
import com.example.final_project.reservation.Reservation;
import com.example.final_project.reservation.ReservationRepository;
import com.example.final_project.reservation.ReservationResponse;
import com.example.final_project.review.Review;
import com.example.final_project.review.ReviewRepository;
import com.example.final_project.stay.Stay;
import com.example.final_project.stay.StayRepository;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.final_project._core.enums.UserEnum.BLACK;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final PayRepository payRepository;
    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;
    private final StayRepository stayRepository;

    // 모든 유저 정보 리스트
    public List<AdminResponse.userListDTO> adminUserList(){
        List<User> userList = userRepository.findAll();

        List<AdminResponse.userListDTO> respDTO = userList.stream().map(user -> {
            return  new AdminResponse.userListDTO(user);
        }).collect(Collectors.toList());

        return respDTO;
    }

    // 개인 회원을 클릭했을 때, 그 회원의 예약 정보 리스트
    public List<ReservationResponse.DetailDTO> adminReservationList(Integer userId){
        List<Reservation> reservationList = reservationRepository.findByUserIdWithRoomAndStay(userId);

        List<ReservationResponse.DetailDTO> respDTO = reservationList.stream().map(r -> {
            Reservation reservation = reservationRepository.findByReservationIdWithRoomAndStay(r.getId());
            Optional<Pay> payOP = payRepository.findByReservationId(reservation.getId());
            Pay pay = null;
            if (payOP.isPresent()) pay = payOP.get();
            return new ReservationResponse.DetailDTO(reservation, reservation.getRoom(), pay);
        }).collect(Collectors.toList());

        return respDTO;
    }


    // 모든 기업 정보 리스트
    public List<AdminResponse.companyListDTO> adminCompanyList(){
        List<Company> companyList = companyRepository.findAll();

        List<AdminResponse.companyListDTO> respDTO = companyList.stream().map(company -> {
            return new AdminResponse.companyListDTO(company);
        }).collect(Collectors.toList());

        return respDTO;
    }

    // 블랙 리스트에 추가 (개인)
    public void addUserBlackList(Integer userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));
        user.setState(BLACK);
        userRepository.save(user);
    }

    // 블랙 리스트에 추가 (기업)
    public void addCompanyBlackList(Integer companyId){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));
        company.setState(CompanyEnum.BLACK);
        companyRepository.save(company);
    }

    // 특정 개인이 쓴 리뷰 내역 조회
    public List<Review> adminUserReviewList(Integer userId){
        return reviewRepository.findByUserIdWithUserAndRoom(userId);
    }

//    // 특정 기업의 숙소 리스트
//    public List<Stay> adminCompanyStayList(Integer companyId){
//        List<Stay> stayList = stayRepository.findByCompanyId(companyId);
//
//        return stayRepository.findByCompanyId(companyId);
//    }
}

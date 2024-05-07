package com.example.final_project.admin;

import com.example.final_project._core.enums.CompanyEnum;
import com.example.final_project._core.enums.UserEnum;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.company.Company;
import com.example.final_project.company.CompanyRepository;
import com.example.final_project.company.SessionCompany;
import com.example.final_project.pay.Pay;
import com.example.final_project.pay.PayRepository;
import com.example.final_project.pay.PayResponse;
import com.example.final_project.reservation.Reservation;
import com.example.final_project.reservation.ReservationRepository;
import com.example.final_project.reservation.ReservationResponse;
import com.example.final_project.review.Review;
import com.example.final_project.review.ReviewRepository;
import com.example.final_project.room.RoomRepository;
import com.example.final_project.stay.StayRepository;
import com.example.final_project.stay_image.StayImageRepository;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final RoomRepository roomRepository;
    private final StayImageRepository stayImageRepository;

    // 모든 유저 정보 리스트
    public List<AdminResponse.userListDTO> adminUserList() {
        List<User> userList = userRepository.findAll();

        List<AdminResponse.userListDTO> respDTO = userList.stream().map(user -> {
            return new AdminResponse.userListDTO(user);
        }).collect(Collectors.toList());

        return respDTO;
    }

    // 개인 회원을 클릭했을 때, 그 회원의 예약 정보 리스트
    public List<AdminResponse.userReservationDTO> adminReservationList(Integer userId) {
        List<Reservation> reservationList = reservationRepository.findByUserIdWithRoomAndStay(userId);

        List<AdminResponse.userReservationDTO> respDTO = reservationList.stream().map(r -> {
            return new AdminResponse.userReservationDTO(r, r.getRoom());
        }).collect(Collectors.toList());

        return respDTO;
    }


    // 특정 회원의 예약 정보 상세보기
    public AdminResponse.userReservationDetailDTO adminReservationDetailList(Integer reservationId) {
        Reservation reservation = reservationRepository.findByReservationIdWithRoomAndStay(reservationId);
        Optional<Pay> payOP = payRepository.findByReservationId(reservation.getId());
        Pay pay = null;
        if (payOP.isPresent()) pay = payOP.get();
        AdminResponse.userReservationDetailDTO respDTO= new AdminResponse.userReservationDetailDTO(reservation, reservation.getRoom(), pay);

        return respDTO;
    }

    // 모든 기업 정보 리스트
    public List<AdminResponse.companyListDTO> adminCompanyList() {
        List<Company> companyList = companyRepository.findAll();

        List<AdminResponse.companyListDTO> respDTO = companyList.stream().map(company -> {
            return new AdminResponse.companyListDTO(company);
        }).collect(Collectors.toList());

        return respDTO;
    }

    // 블랙 리스트에 추가 (개인)
    @Transactional
    public void addUserBlackList(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));
        user.setState(UserEnum.BLACK);
        userRepository.save(user);
    }

    // 블랙 리스트에 추가 (기업)
    public void addCompanyBlackList(Integer companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));
        company.setState(CompanyEnum.BLACK);
        companyRepository.save(company);
    }

    // 특정 개인이 쓴 리뷰 내역 조회
    public List<Review> adminUserReviewList(Integer userId) {
        return reviewRepository.findByUserIdWithUserAndRoom(userId);
    }


//    public List<AdminResponse.companyStayListDTO> adminCompanyStayList(Integer companyId){
//        List<Stay> stayList = stayRepository.findByCompanyId(companyId);
//        List<AdminResponse.companyStayListDTO> respDTO = stayList.stream().map(stay -> {
//            List<Room> rooms = roomRepository.findByStayId(stay.getId());
//            return new AdminResponse.companyStayListDTO(stay,rooms);
//        }).collect(Collectors.toList());
//
//        return respDTO;
//    }

    // 기업 가입 거절
    @Transactional
    public void rejectJoinCompany(Integer companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));
        company.setState(CompanyEnum.REJECT);
        companyRepository.save(company);
    }

    // 기업 가입 승인
    @Transactional
    public void activeJoinCompany(Integer companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));
        company.setState(CompanyEnum.ACTIVE);
        companyRepository.save(company);
    }

    // 블랙 리스트 버튼 기능 구현
    @Transactional
    public SessionCompany adminCompanyBlack(Integer companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));

        company.setState(CompanyEnum.BLACK);
        companyRepository.save(company);

        return new SessionCompany(company);
    }

    // 블랙 리스트 취소 버튼 기능 구현
    @Transactional
    public SessionCompany adminCompanyBlackCancel(Integer companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));

        company.setState(CompanyEnum.ACTIVE);
        companyRepository.save(company);

        return new SessionCompany(company);
    }

    // 기업 수익 전체 조회
    public PayResponse.TotalIncomeDTO findIncomeByStayAndTotalIncome(SessionCompany sessionCompany) {
        Company company = companyRepository.findById(sessionCompany.getId())
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));

        // 전체 수익 가져오기
        PayResponse.TotalIncomeDTO respDTO = payRepository.findTotalIncome(company.getId());

        // 만약 수익이 전혀 없으면 0을 반환
        if (respDTO == null) {
            respDTO = new PayResponse.TotalIncomeDTO(company.getId(), 0L, 0L);
        }

        return respDTO;
    }

    // 숙소 수익 전체 조회
    public PayResponse.TotalIncomeDTO findIncomeByStayAndTotalIncome(SessionCompany sessionCompany, Integer stayId) {
        Company company = companyRepository.findById(sessionCompany.getId())
                .orElseThrow(() -> new Exception404("존재 하지 않는 계정입니다"));
        //Stay stay = stayRepository.findById()

        // 전체 수익 가져오기
        PayResponse.TotalIncomeDTO respDTO = payRepository.findTotalIncome(company.getId());

        // 만약 수익이 전혀 없으면 0을 반환
        if (respDTO == null) {
            //respDTO = new PayResponse.StayTotalIncomeDTO(company.getId(), ,0L, 0L);
        }

        return respDTO;
    }

    // 개인이 작성한 리뷰 정보 리스트
    public List<AdminResponse.userReviewListDTO> findReviewByUserId (Integer userId) {
        List<Review> reviewList = reviewRepository.findByUserIdWithUserAndRoom(userId);
        List<AdminResponse.userReviewListDTO> respDTO = reviewList.stream().map(review -> {
            return new AdminResponse.userReviewListDTO(review);
        }).collect(Collectors.toList());
        return respDTO;
    }
}

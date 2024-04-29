package com.example.final_project.admin;

import com.example.final_project._core.enums.CompanyEnum;
import com.example.final_project._core.enums.UserEnum;
import com.example.final_project.company.Company;
import com.example.final_project.company.CompanyRepository;
import com.example.final_project.reservation.ReservationResponse;
import com.example.final_project.review.Review;
import com.example.final_project.user.User;
import com.example.final_project.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static com.example.final_project._core.enums.CompanyEnum.PROGRESSING;
import static com.example.final_project._core.enums.CompanyEnum.REJECT;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    AdminService adminService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void adminUserList_test(){
        // given


        // when
        List<AdminResponse.userListDTO> userList = adminService.adminUserList();

        // eye
        System.out.println("adminUserList_test size : " + userList.size());
        System.out.println("adminUserList_test userList : " + userList);

        // then
        Assertions.assertThat(userList.getFirst().getEmail()).isEqualTo("ssar@nate.com");

    }

    @Test
    public void adminReservationList_test(){
        // given
        Integer userId = 1;

        // when
        List<ReservationResponse.DetailDTO> reservationList = adminService.adminReservationList(userId);

        // eye
        System.out.println("adminReservationList_test size : " + reservationList.size());
        System.out.println("adminReservationList_test getLast.id : " + reservationList.getLast().getReservationId());

        // then
        Assertions.assertThat(reservationList.getLast().getReservationName()).isEqualTo("홍길동");

    }


    @Test
    public void adminCompanyList_test(){
        // given


        // when
        List<AdminResponse.companyListDTO> companyList = adminService.adminCompanyList();

        // eye
        System.out.println("adminCompanyList_test size : " + companyList.size());
        System.out.println("adminCompanyList_test companyList : " + companyList);

        // then
        Assertions.assertThat(companyList.getFirst().getName()).isEqualTo("김회사");

    }

    @Test
    public void addUserBlackList_test(){
        // given
        Integer userId = 1;

        // when
        adminService.addUserBlackList(userId);

        // eye
        Optional<User> userOP = userRepository.findById(userId);
        User user = null;
        if(userOP.isPresent()){
            user = userOP.get();
        }
        System.out.println("addUserBlackList_test : " + user);

        // then
        Assertions.assertThat(user.getState()).isEqualTo(UserEnum.BLACK);

    }

    @Test
    public void addCompanyBlackList_test(){
        // given
        Integer companyId = 1;

        // when
        adminService.addCompanyBlackList(companyId);

        // eye
        Optional<Company> companyOP = companyRepository.findById(companyId);
        Company company = null;
        if(companyOP.isPresent()){
            company = companyOP.get();
        }
        System.out.println("addCompanyBlackList_test : " + company);

        // then
        Assertions.assertThat(company.getState()).isEqualTo(CompanyEnum.BLACK);

    }

    @Test
    public void adminUserReviewList_test(){
        // given
        Integer userId = 1;

        // when
        List<Review> reviewList = adminService.adminUserReviewList(userId);

        // eye
        System.out.println("adminUserReviewList_test size : " + reviewList.size());

        // then
        Assertions.assertThat(reviewList.getLast().getContent()).isEqualTo("괜찮은 편이에요.");

    }

    @Test
    public void adminCompanyStayList_test(){
        // given
        Integer companyId = 1;

        // when
        List<AdminResponse.companyStayListDTO> stayList = adminService.adminCompanyStayList(companyId);

        // eye
        System.out.println("adminCompanyStayList_test stayList size : " + stayList.size());
        System.out.println("adminCompanyStayList_test getFirst.rooms size : " + stayList.getFirst().getRooms().size());
        System.out.println("adminCompanyStayList_test getFirst.getRooms.getFirst.getId: " + stayList.getFirst().getRooms().getFirst().getId());
        System.out.println("adminCompanyStayList_test getFirst.getRooms.getLast.getId: " + stayList.getFirst().getRooms().getLast().getId());

        // then
        Assertions.assertThat(stayList.getFirst().getRooms().getFirst().getName()).isEqualTo("스위트룸");

    }

    @Test
    public void rejectJoinCompany_test(){
        // given
        Company company = Company.builder()
                .id(4)
                .email("com4@nate.com")
                .password("1234")
                .businessName("서스테이회사")
                .businessNumber("723-78-12743")
                .businessAddress("부산광역시")
                .phone("01044443333")
                .name("서회사")
                .state(PROGRESSING)
                .reportCount(0)
                .build();
        companyRepository.save(company);

        // when
        adminService.rejectJoinCompany(company.getId());

        // eye
        Optional<Company> companyOP = companyRepository.findById(company.getId());

        Company updatedCompany = null;
        if(companyOP.isPresent()){
            updatedCompany = companyOP.get();
        }

        System.out.println("rejectJoinCompany_test company : " + updatedCompany);

        // then
        Assertions.assertThat(updatedCompany.getState()).isEqualTo(REJECT);

    }

    @Test
    public void activeJoinCompany_test(){
        // given
        Company company = Company.builder()
                .id(4)
                .email("com4@nate.com")
                .password("1234")
                .businessName("서스테이회사")
                .businessNumber("723-78-12743")
                .businessAddress("부산광역시")
                .phone("01044443333")
                .name("서회사")
                .state(PROGRESSING)
                .reportCount(0)
                .build();
        companyRepository.save(company);

        // when
        adminService.activeJoinCompany(company.getId());

        // eye
        Optional<Company> updatedcompanyOP = companyRepository.findById(company.getId());
        Company updatedcompany = null;
        if (updatedcompanyOP.isPresent()){
            updatedcompany = updatedcompanyOP.get();
        }
        System.out.println("activeJoinCompany_test : " + updatedcompany);

        // then


    }
}

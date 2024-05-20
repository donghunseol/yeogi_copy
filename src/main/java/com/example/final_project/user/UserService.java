package com.example.final_project.user;

import com.example.final_project._core.errors.exception.Exception400;
import com.example.final_project._core.errors.exception.Exception401;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project._core.utils.JwtUtil;
import com.example.final_project.faq.Faq;
import com.example.final_project.faq.FaqRepository;
import com.example.final_project.question.Question;
import com.example.final_project.question.QuestionRepository;
import com.example.final_project.reservation.Reservation;
import com.example.final_project.reservation.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final FaqRepository faqRepository;
    private final QuestionRepository questionRepository;

    // 로그인 기능
    public String login(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

        String jwt = JwtUtil.userCreate(user);
        JwtUtil.userVerify(jwt);

        return jwt;
    }


    public Optional<User> findByEmail(String email){
        
        Optional<User> userOP = userRepository.findByEmail(email);

        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저네임입니다");
        }

        return userOP;

    }


    // 회원 가입
    @Transactional
    public String joinAndLogin(UserRequest.JoinDTO reqDTO) {

        // 회원 가입
        User joinUser = userRepository.save(reqDTO.toEntity());

        // 로그인
        String jwt = JwtUtil.userCreate(joinUser);
        JwtUtil.userVerify(jwt);

        return jwt;
    }

    // 회원 정보 수정
    @Transactional
    public SessionUser update(SessionUser sessionUser, UserRequest.UpdateDTO reqDTO, Integer userId) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재 하지 않는 유저입니다"));

        // 권한 체크
        if (user.getId() != userId) {
            throw new Exception401("유저 정보를 수정할 권한이 없습니다");
        }

        // 수정
        user.updateUser(reqDTO);

        return new SessionUser(user);
    }

    public UserResponse.LoginDTO loginByDTO(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

        return new UserResponse.LoginDTO(user);
    }


    public UserResponse.JoinDTO joinByDTO(UserRequest.JoinDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

        return new UserResponse.JoinDTO(user);
    }

    public List<UserResponse.Notifications> notifications(SessionUser sessionUser){
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재 하지 않는 유저입니다"));
        List<Reservation> reservationList = reservationRepository.findByUserId(user.getId());
        return reservationList.stream().map(UserResponse.Notifications::new).collect(Collectors.toList());
    }

    public List<UserResponse.FaqListDTO> faqList(){

        List<Faq> faqList =  faqRepository.findAllByExcludeComapny();

        return faqList.stream().map(UserResponse.FaqListDTO::new).toList();
    }

    // 객실 별 예약 조회 (달력)
    public List<UserResponse.ReservationForCalendarDTO> reservationForCalendar(Integer roomId){
        List<Reservation> reservationList = reservationRepository.findReservationsByRoomId(roomId);
        return reservationList.stream().map(UserResponse.ReservationForCalendarDTO::new).collect(Collectors.toList());
    }

    // 문의사항 작성
    @Transactional
    public void questionWrite(SessionUser sessionUser,UserRequest.QuestionSave reqDTO){
        //1. 인증처리
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재 하지 않는 유저입니다"));
        //2. 권한처리
        if (user == null){
            throw new Exception401("문의할 권한이 없습니다.");
        }

        //3. 저장
        questionRepository.save(reqDTO.toEntity(user));

    }
}

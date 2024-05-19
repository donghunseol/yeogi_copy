package com.example.final_project.reservation;

import com.example.final_project.room.Room;
import com.example.final_project.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

import static com.example.final_project._core.enums.RoomEnum.APPLIED;

public class ReservationRequest {

    // 예약하기 DTO
    @Data
    public static class DTO {
        @NotEmpty(message = "숙소를 예약한 유저가 존재하지 않습니다")
        private Integer userId; // 예약한 유저의 번호

        @NotEmpty(message = "예약할 방이 존재하지 않습니다")
        private Integer roomId; // 예약된 방번호

        @NotEmpty(message = "입실 날짜를 선택해주세요")
        @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}")
        private LocalDate checkInDate; // 입실 날짜

        @NotEmpty(message = "퇴실 날짜를 선택해주세요")
        @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}")
        private LocalDate checkOutDate; // 퇴실 날짜

        @NotEmpty(message = "예약자 대표 이름을 입력해주세요")
        @Size(min = 2, max = 10)
        private String reservationName; // 예약자 대표 이름

        @NotEmpty(message = "전화번호를 입력해주세요")
        @Pattern(regexp = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}")
        private String reservationTel; // 예약자 전화번호

        @NotEmpty(message = "객실 가격을 입력해주세요")
        private Integer amountToPay; // 결제할 객실 가격

        // 예약한 유저와 예약할 방을 매개변수로 받는다
        public Reservation toEntity(User user, Room room) {
            if(room.getSpecialState().equals(APPLIED)) {
                return Reservation.builder()
                        .user(user)
                        .room(room)
                        .checkInDate(this.checkInDate)
                        .checkOutDate(this.checkOutDate)
                        .reservationName(this.reservationName)
                        .reservationTel(this.reservationTel)
                        .amountToPay(room.getSpecialPrice())
                        .build();
            }else{
                return Reservation.builder()
                        .user(user)
                        .room(room)
                        .checkInDate(this.checkInDate)
                        .checkOutDate(this.checkOutDate)
                        .reservationName(this.reservationName)
                        .reservationTel(this.reservationTel)
                        .amountToPay(room.getPrice())
                        .build();
            }

        }
    }

    @Data
    public static class UpdateDTO {
        @NotEmpty(message = "예약자 대표 이름을 입력해주세요")
        @Size(min = 2, max = 10)
        private String reservationName; // 예약자 대표 이름

        @NotEmpty(message = "전화번호를 입력해주세요")
        @Pattern(regexp = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}")
        private String reservationTel; // 예약자 전화번호

        // 예약자 대표 이름과 예약자 전화 번호만 수정이 가능해서 매개변수를 받지 않는다
        public Reservation toEntity() {
            return Reservation.builder()
                    .reservationName(this.reservationName)
                    .reservationTel(this.reservationTel)
                    .build();
        }
    }
}

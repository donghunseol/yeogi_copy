package com.example.final_project.stay;

import com.example.final_project._core.enums.StayEnum;
import com.example.final_project.company.Company;
import com.example.final_project.option.Option;
import com.example.final_project.room.Room;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "stay_tb")
@Entity
@JsonIgnoreProperties({"company", "options", "rooms"})
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 숙소 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company; // 해당 숙소의 기업

    @Column(nullable = false)
    private String name; // 숙소 이름

    @Column(nullable = false)
    private String category; // 숙소 분류

    @Column(nullable = false)
    private String address; // 숙소 주소

    private String gps; // 나중에 추가할 필드(좌표)

    @Enumerated(EnumType.STRING)
    // @Column(nullable = false)
    private StayEnum state; // 상태 (TRUE : 사용 / FALSE : 탈퇴)

    @Column(nullable = false)
    private String intro; // 숙소 소개

    @Column(nullable = false)
    private String information; // 숙소 이용 정보

    @OneToMany(mappedBy = "stay", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Option> options = new ArrayList<>(); // 옵션 리스트

    @OneToMany(mappedBy = "stay", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Room> rooms = new ArrayList<>(); // 방(객실) 옵션 리스트

    @CreationTimestamp
    private LocalDateTime createdAt; // 숙소 등록 일자

    @Builder
    public Stay(Integer id, Company company, String name, String category, String address, String intro, String information, List<Option> options, LocalDateTime createdAt, StayEnum state) {
        this.id = id;
        this.company = company;
        this.name = name;
        this.category = category;
        this.address = address;
        this.intro = intro;
        this.information = information;
        this.options = options;
        this.createdAt = createdAt;
        this.state = state;
    }

    public void updateStay(StayRequest.UpdateDTO reqDTO) {
        this.intro = reqDTO.getIntro();
        this.information = reqDTO.getInformation();
    }


    public void deleteStay(StayEnum state) {
        if (state == StayEnum.TRUE) {
            this.state = StayEnum.FALSE;
        }
    }
}
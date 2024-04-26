package com.example.final_project.option;

import com.example.final_project.stay.Stay;
import com.example.final_project.stay.StayRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "option_tb")
@Entity
@JsonIgnoreProperties({"stay"})
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 옵션 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Stay stay; // 해당 숙소

    @Column(nullable = false)
    private String name; // 옵션 이름

    @Column(nullable = false)
    private String iconName; // 옵션 아이콘 이름

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Option(Integer id, Stay stay, String name, String iconName, LocalDateTime createdAt) {
        this.id = id;
        this.stay = stay;
        this.name = name;
        this.iconName = iconName;
        this.createdAt = createdAt;
    }

}
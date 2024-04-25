package com.example.final_project.stay;

import com.example.final_project.company.Company;
import com.example.final_project.option.Option;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

public class StayRequest {

    @Data
    public static class SaveDTO{
        private Integer companyId;
        private String name;
        private String category;
        private String address;
        private String intro;
        private String information;
        private String imageName;
        private String imagePath;
        private List<Option> options;
        private LocalDateTime createdAt;

        public Stay toEntity(Company company, List<Option> options){
            return Stay.builder()
                    .name(name)
                    .category(category)
                    .address(address)
                    .intro(intro)
                    .information(information)
                    .imageName(imageName)
                    .imagePath(imagePath)
                    .createdAt(createdAt)
                    .company(company)
                    .options(options)
                    .build();
        }
    }
}

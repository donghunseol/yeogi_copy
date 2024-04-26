package com.example.final_project.stay;

import com.example.final_project.company.Company;
import com.example.final_project.option.Option;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

public class StayRequest {

    //숙소등록
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
        private List<Option> optionList;
        private LocalDateTime createdAt;

        public Stay toEntity(Company company){
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
                    .options(optionList)
                    .build();
        }
    }

    //숙소수정
    // 이름,분류,주소 등은 삭제 후 등록하는게 맞는거 같아서 뺐어요!!
    @Data
    public static class UpdateDTO{
        private String intro;
        private String information;
        private String imageName;
        private String imagePath;
        private List<Option> optionList;
        private LocalDateTime createdAt;
    }
}

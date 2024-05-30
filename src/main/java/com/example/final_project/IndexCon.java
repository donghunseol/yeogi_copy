package com.example.final_project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexCon {
    @GetMapping("/health")
    public String dfdsafasdfas(){
        return "ok";
    }
}

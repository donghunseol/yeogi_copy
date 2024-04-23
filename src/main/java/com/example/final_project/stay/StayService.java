package com.example.final_project.stay;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StayService {

    private final StayRepository stayRepository;
}

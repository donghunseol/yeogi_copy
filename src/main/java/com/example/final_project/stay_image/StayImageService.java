package com.example.final_project.stay_image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StayImageService {

    private final StayImageRepository stayImageRepository;
}

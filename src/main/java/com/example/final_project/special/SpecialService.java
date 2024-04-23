package com.example.final_project.special;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpecialService {

    private final SpecialRepository specialRepository;
}

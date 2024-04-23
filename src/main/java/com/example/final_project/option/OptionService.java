package com.example.final_project.option;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OptionService {

    private final OptionRepository optionRepository;
}

package com.example.final_project.room_information;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomInformationService {
    private final RoomInformationRepository roomInformationRepository;
}

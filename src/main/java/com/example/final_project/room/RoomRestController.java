package com.example.final_project.room;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.user.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RoomRestController {
    private final RoomService roomService;

    //객실 상세정보
    @GetMapping("/room/detail/{roomId}")
    public ResponseEntity<?> detail(@PathVariable Integer roomId){

        RoomResponse.Detail respDTO = roomService.detail(roomId);

        return ResponseEntity.ok().body(new ApiUtil<>(respDTO));
    }

}

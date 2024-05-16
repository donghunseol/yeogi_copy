package com.example.final_project.event;

import com.example.final_project._core.utils.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EventRestController {

    private final EventService eventService;

    //이벤트 리스트
    @GetMapping("/event")
    public ResponseEntity<?> eventList(){

        List<EventResponse.EventListDTO> respDTO = eventService.eventList();

        return ResponseEntity.ok().body(new ApiUtil<>(respDTO));
    }


    //이벤트 디테일
    @GetMapping("/event/detail/{eventId}")
    public ResponseEntity<?> detail(@PathVariable Integer eventId){

        EventResponse.EventDetailDTO respDTO = eventService.eventDetail(eventId);

        return ResponseEntity.ok().body(new ApiUtil<>(respDTO));
    }
}

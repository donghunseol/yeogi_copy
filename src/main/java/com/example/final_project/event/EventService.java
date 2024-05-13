package com.example.final_project.event;

import com.example.final_project._core.enums.EventEnum;
import com.example.final_project._core.errors.exception.Exception404;
import com.example.final_project.stay.StayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    //이벤트 디테일
    public EventResponse.EventDetailDTO eventDetail(Integer eventId){

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new Exception404("해당 이벤트를 찾을 수 없습니다"));

        return new EventResponse.EventDetailDTO(event);
    }


    //이벤트 리스트
    public List<EventResponse.EventListDTO> eventList(){

        List<Event> eventList = eventRepository.findAll();

        List<EventResponse.EventListDTO> resultList = eventList.stream()
                .filter(event -> event.getState() == EventEnum.Enable)
                .map(EventResponse.EventListDTO::new).toList();

        return resultList;
    }
}

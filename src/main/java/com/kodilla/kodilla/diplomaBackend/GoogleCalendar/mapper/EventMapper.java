package com.kodilla.kodilla.diplomaBackend.GoogleCalendar.mapper;

import com.kodilla.kodilla.diplomaBackend.GoogleCalendar.domain.EventDto;
import com.google.api.services.calendar.model.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class EventMapper {

    public EventDto mapToEventDto(Event event){
        return new EventDto(LocalDate.parse(event.getStart().getDate().toString()), //.getDate() na wypadek, gdyby getStarted zwróciło DateTIME
                            LocalDate.parse(event.getEnd().getDate().toString()),
                            event.getDescription());
    }

    public List<EventDto> mapToEventDtoList(List<Event> events){
        return events.stream()
                .map(event -> mapToEventDto(event))
                .collect(Collectors.toList());
    }
}

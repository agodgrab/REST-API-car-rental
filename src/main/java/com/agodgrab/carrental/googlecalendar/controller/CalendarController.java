package com.agodgrab.carrental.googlecalendar.controller;


import com.agodgrab.carrental.dto.RentDto;
import com.agodgrab.carrental.googlecalendar.domain.EventDto;
import com.agodgrab.carrental.googlecalendar.mapper.EventMapper;
import com.agodgrab.carrental.googlecalendar.service.CalendarService;
import com.agodgrab.carrental.mapper.RentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private EventMapper eventMapper;

    @PostMapping(value = "/event")
    public String createCalendarEvent(@RequestBody RentDto rentDto) {
        return calendarService.addEvent(rentMapper.mapToEntity(rentDto));
    }

    @GetMapping(value = "/upcomingEvents")
    public List<EventDto> getUpcomingEvents() {
        return eventMapper.mapToEventDtoList(calendarService.listUpcomingEvents());
    }

    @DeleteMapping(value = "/event")
    public void deleteCalendarEvent(@RequestParam String eventId) {
        calendarService.deleteEvent(eventId);
    }

}

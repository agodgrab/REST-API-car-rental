package com.kodilla.kodilla.diplomaBackend.GoogleCalendar.service;

import com.kodilla.kodilla.diplomaBackend.GoogleCalendar.client.CalendarClient;
import com.kodilla.kodilla.diplomaBackend.Rent.domain.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.services.calendar.model.Event;
import java.util.List;

@Service
public class CalendarService {

    @Autowired
    CalendarClient calendarClient;

    public String addEvent(Rent rent){
        return calendarClient.createRentEvent(rent);
    }

    public List<Event> listUpcomingEvents(){
        return calendarClient.list10nextEvents();
    }

    public void deleteEvent(String eventId){
        calendarClient.deleteRentEvent(eventId);
    }
}

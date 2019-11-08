package com.agodgrab.carrental.googlecalendar.service;

import com.agodgrab.carrental.domain.Rent;
import com.agodgrab.carrental.googlecalendar.client.CalendarClient;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    @Autowired
    CalendarClient calendarClient;

    public String addEvent(Rent rent) {
        return calendarClient.createRentEvent(rent);
    }

    public List<Event> listUpcomingEvents() {
        return calendarClient.list10nextEvents();
    }

    public void deleteEvent(String eventId) {
        calendarClient.deleteRentEvent(eventId);
    }
}

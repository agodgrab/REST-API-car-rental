package com.kodilla.kodilla.diplomaBackend.googlecalendar.service;

import com.google.api.services.calendar.model.Event;
import com.kodilla.kodilla.diplomaBackend.domain.Rent;
import com.kodilla.kodilla.diplomaBackend.googlecalendar.client.CalendarClient;
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

package com.agodgrab.carrental.googlecalendar.client;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.agodgrab.carrental.domain.Rent;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;


@Component
public class CalendarClient {

    private static final String APPLICATION_NAME = "Car Rental Calendar API";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final String TIME_ZONE = "Europe/Warsaw";
    private static final int PORT_NUMBER = 8888;
    private static final String ACCESS_TYPE = "offline";

    private static final String CALENDAR_ID = "4ueb78lf25kffagn6h89q98e9g@group.calendar.google.com";

    private Calendar buildAuthorizedClientService() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        return service;
    }


    public String createRentEvent(Rent rent) {

        Event event = new Event()
                .setSummary(rent.getCarRented().getCategory().getName() + ": " + rent.getCarRented().getModel())
                .setDescription("Rent for: " + rent.getUser().getFirstname() + " " + rent.getUser().getLastname());

        DateTime startDate = new DateTime(rent.getStartDay().toString());
        EventDateTime start = new EventDateTime()
                .setDate(startDate)
                .setTimeZone(TIME_ZONE);
        event.setStart(start);

        DateTime endDateTime = new DateTime(rent.getEndDay().toString());
        EventDateTime end = new EventDateTime()
                .setDate(endDateTime)
                .setTimeZone(TIME_ZONE);
        event.setEnd(end);

        try {
            Calendar service = buildAuthorizedClientService();
            event = service.events().insert(CALENDAR_ID, event).execute();
        } catch (Exception e) {
            System.out.println("Creating Service exception messeage: " + e.getMessage() + ", exception cause: " + e.getCause());
        }

        System.out.printf("Event created: %s\n", event.getHtmlLink());
        return event.getHtmlLink() + "\n" + "eventId: " + event.getId();
    }

    public List<Event> list10nextEvents() {
        DateTime now = new DateTime(System.currentTimeMillis());
        try {
            Calendar service = buildAuthorizedClientService();
            Events events = service.events().list(CALENDAR_ID)
                    .setMaxResults(10)
                    .setTimeMin(now)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();
            List<Event> items = events.getItems();
            if (items.isEmpty()) {
                System.out.println("No upcoming events found.");
            } else {
                System.out.println("Upcoming events");
                for (Event event : items) {
                    DateTime start = event.getStart().getDateTime();
                    if (start == null) {
                        start = event.getStart().getDate();
                    }
                    System.out.printf("%s (%s)\n", event.getSummary(), start);
                }
            }
            return items;
        } catch (Exception e) {
            System.out.println("Creating Service exception message: " + e.getMessage() + ", exception cause: " + e.getCause());
            return null;
        }
    }

    public void deleteRentEvent(String eventId) {
        try {
            Calendar service = buildAuthorizedClientService();
            service.events().delete(CALENDAR_ID, eventId);
        } catch (Exception e) {
            System.out.println("Creating Service exception message: " + e.getMessage() + ", exception cause: " + e.getCause());
        }
    }

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarClient.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType(ACCESS_TYPE)
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(PORT_NUMBER).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

}

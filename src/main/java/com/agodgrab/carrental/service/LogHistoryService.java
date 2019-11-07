package com.kodilla.kodilla.diplomaBackend.service;

import com.kodilla.kodilla.diplomaBackend.domain.LogHistory;
import com.kodilla.kodilla.diplomaBackend.domain.User;
import com.kodilla.kodilla.diplomaBackend.repository.LogHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class LogHistoryService {

    @Autowired
    private LogHistoryRepository logHistoryRepository;

    public void saveLog(User user, String message) {
        LogHistory logHistory = new LogHistory();
        logHistory.setUser(user);

        Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        logHistory.setTimeStamp(currentTimestamp);
        logHistory.setLogMessage(message);

        logHistoryRepository.save(logHistory);
    }
}

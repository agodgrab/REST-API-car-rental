package com.agodgrab.carrental.service;

import com.agodgrab.carrental.domain.LogHistory;
import com.agodgrab.carrental.domain.User;
import com.agodgrab.carrental.repository.LogHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class LogHistoryService {

    @Autowired
    private LogHistoryRepository logHistoryRepository;

    public void saveLog(User user, String message) {
        LogHistory logHistory = new LogHistory();
        logHistory.setUser(user);

        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        logHistory.setTimeStamp(currentTimestamp);
        logHistory.setLogMessage(message);

        logHistoryRepository.save(logHistory);
    }
}

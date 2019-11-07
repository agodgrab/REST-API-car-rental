package com.kodilla.kodilla.diplomaBackend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "LOGS_HISTORY")
public class LogHistory {

    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TIME_STAMP")
    private Timestamp timeStamp;

    @Column(name = "LOG_MESSAGE")
    private String logMessage;

    public LogHistory(User user, Timestamp timeStamp, String logMessage) {
        this.user = user;
        this.timeStamp = timeStamp;
        this.logMessage = logMessage;
    }

    public LogHistory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }
}

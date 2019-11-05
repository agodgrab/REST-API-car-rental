package com.kodilla.kodilla.diplomaBackend.User.service;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }
}

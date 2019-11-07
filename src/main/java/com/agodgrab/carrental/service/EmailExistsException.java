package com.kodilla.kodilla.diplomaBackend.service;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }
}

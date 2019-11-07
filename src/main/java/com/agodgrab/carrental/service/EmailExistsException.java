package com.agodgrab.carrental.service;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }
}

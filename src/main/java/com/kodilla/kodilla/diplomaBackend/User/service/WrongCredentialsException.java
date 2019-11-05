package com.kodilla.kodilla.diplomaBackend.User.service;

public class WrongCredentialsException extends Exception {

    public WrongCredentialsException(String message) {
        super(message);
    }
}


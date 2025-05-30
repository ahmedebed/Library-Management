package com.example.Library_Management.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(long aid) {
        super("User with ID [ " + aid + " ] is NOT Found");
    }

}

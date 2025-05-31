package com.example.Library_Management.exception;

public class AlreadyReturnedException extends RuntimeException {
    public AlreadyReturnedException(String message) {
        super(message);
    }
}

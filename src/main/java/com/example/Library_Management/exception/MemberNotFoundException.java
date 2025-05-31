package com.example.Library_Management.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String message) {
        super(message);
    }
    public MemberNotFoundException(long aid) {
        super("Member with ID [ " + aid + " ] is NOT Found");
    }
}

package com.example.Library_Management.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
    public AuthorNotFoundException(Long id) {
        super("Author With id : " + id + " Not Found");
    }
}

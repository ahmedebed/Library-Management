package com.example.Library_Management.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
    public BookNotFoundException(Long id) {
        super("Book With id : " + id + " Not Found");
    }
}

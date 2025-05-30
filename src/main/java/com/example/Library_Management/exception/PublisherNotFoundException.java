package com.example.Library_Management.exception;

public class PublisherNotFoundException extends RuntimeException {
    public PublisherNotFoundException(String message) {
        super(message);
    }
    public PublisherNotFoundException(Long id) {
        super("Publisher With id :" + id + " Not Found");
    }
}

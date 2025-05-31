package com.example.Library_Management.exception;

public class BorrowingTransactionNotFoundException extends RuntimeException {
    public BorrowingTransactionNotFoundException(String message) {
        super(message);
    }
}

package com.hrd.categoryservice.exception;

public class InvalidJWTException extends RuntimeException {
    public InvalidJWTException(String message) {
        super(message);
    }
}

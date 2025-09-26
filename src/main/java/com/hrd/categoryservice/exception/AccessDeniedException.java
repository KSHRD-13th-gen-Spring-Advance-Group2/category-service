package com.hrd.categoryservice.exception;

/**
 * Developed by ChhornSeyha
 * Date: 28/08/2025
 */

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message){
        super(message);
    }
}

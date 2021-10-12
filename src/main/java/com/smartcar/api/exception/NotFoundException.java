package com.smartcar.api.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

package com.smartcar.api.exception;

public class ServiceOutageException extends RuntimeException{
    public ServiceOutageException(String errorMessage) {
        super(errorMessage);
    }
}

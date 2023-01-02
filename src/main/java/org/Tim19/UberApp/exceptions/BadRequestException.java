package org.Tim19.UberApp.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException() {}

    public BadRequestException(String message) {
        super(message);
    }

}

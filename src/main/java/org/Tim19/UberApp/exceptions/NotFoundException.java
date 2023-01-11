package org.Tim19.UberApp.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {}

    public NotFoundException(String message) {
        super(message);
    }

}

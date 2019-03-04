package com.epam.cleancode.singleresponsibility;

public class WrongFormatException extends RuntimeException {
    private final String message;


    WrongFormatException(String errorMessage) {
        super();
        message = errorMessage;

    }
    @Override
    public String getMessage() {
        return message;
    }
}
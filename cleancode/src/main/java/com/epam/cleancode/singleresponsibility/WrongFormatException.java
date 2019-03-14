package com.epam.cleancode.singleresponsibility;

public class WrongFormatException extends RuntimeException {
    private final String message;

    WrongFormatException(String exceptionMessage) {
        super();
        message = exceptionMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

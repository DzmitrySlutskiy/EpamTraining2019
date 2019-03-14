package com.epam.cleancode.comments;

public class InvalidInputException extends RuntimeException{

    private final String message;

    InvalidInputException(String exceptionMessage) {
        super();
        message = exceptionMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package com.epam.cleancode.comments;

public class InvalidInputException extends IllegalArgumentException {
    private String message;

    public InvalidInputException(String exceptionMessage) {
        super();

        message = exceptionMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

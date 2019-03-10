package com.epam.cleancode.comments;

public class InvalidInputException extends RuntimeException {

    String message;

    public InvalidInputException(String errorMessage) {
        super();
        message = errorMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

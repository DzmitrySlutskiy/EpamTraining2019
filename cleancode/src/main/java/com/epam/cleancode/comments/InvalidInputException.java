package com.epam.cleancode.comments;

public class InvalidInputException extends RuntimeException{
    private final String message;

    InvalidInputException(String errorMessage) {
        super();
        message = errorMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

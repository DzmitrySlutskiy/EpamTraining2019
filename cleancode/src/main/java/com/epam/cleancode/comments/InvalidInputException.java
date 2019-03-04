package com.epam.cleancode.comments;

public class InvalidInputException extends RuntimeException{
    private final String message;

    public InvalidInputException(String message) {
        super();
        this.message = message;

    }

    @Override
    public String getMessage() {
        return message;
    }

}

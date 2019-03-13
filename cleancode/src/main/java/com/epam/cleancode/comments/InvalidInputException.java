package com.epam.cleancode.comments;

public class InvalidInputException extends RuntimeException{
    private String message;

    InvalidInputException(String error) {
        super();
        this.message = error;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

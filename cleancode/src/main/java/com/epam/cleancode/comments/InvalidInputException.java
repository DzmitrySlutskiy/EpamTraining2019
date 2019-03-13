package com.epam.cleancode.comments;

public class InvalidInputException extends RuntimeException{
    private String message;
    InvalidInputException(String s) {
        super();
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

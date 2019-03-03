package com.epam.cleancode.comments;

public class InvalidInputException extends RuntimeException {

    private String message;

    public InvalidInputException(String msg) {
        super();
        message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

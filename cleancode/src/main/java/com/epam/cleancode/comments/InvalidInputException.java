package com.epam.cleancode.comments;

public class InvalidInputException extends RuntimeException {

    String message;

    public InvalidInputException(String _message) {
        super();
        message = _message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

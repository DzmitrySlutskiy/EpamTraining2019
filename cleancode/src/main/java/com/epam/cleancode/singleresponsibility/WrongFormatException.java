package com.epam.cleancode.singleresponsibility;

public class WrongFormatException extends RuntimeException {

    private String message;

    public WrongFormatException(String _message) {
        super();
        message = _message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package com.epam.cleancode.singleresponsibility;

public class WrongFormatException extends RuntimeException {

    private String message;

    public WrongFormatException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

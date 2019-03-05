package com.epam.cleancode.singleresponsibility;

public class WrongFormatException extends RuntimeException {
    String message;
    public WrongFormatException(String Error) {
        super();
        this.message = Error;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

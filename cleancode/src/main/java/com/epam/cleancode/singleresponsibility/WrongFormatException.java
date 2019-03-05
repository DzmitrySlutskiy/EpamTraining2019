package com.epam.cleancode.singleresponsibility;

public class WrongFormatException extends RuntimeException {

    private String message;

    public WrongFormatException(String s) {
        super();
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

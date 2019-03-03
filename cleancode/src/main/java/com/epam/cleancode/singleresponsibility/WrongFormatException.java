package com.epam.cleancode.singleresponsibility;

public class WrongFormatException extends RuntimeException {

    private String message;

    public WrongFormatException(String msg) {
        super();
        message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

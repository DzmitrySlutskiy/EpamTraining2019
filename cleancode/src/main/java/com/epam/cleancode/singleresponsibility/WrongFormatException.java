package com.epam.cleancode.singleresponsibility;

public class WrongFormatException extends IllegalArgumentException {
    private final String message;

    WrongFormatException(String s) {
        super();
        this.message = s;

    }

    @Override
    public String getMessage() {
        return message;
    }

}

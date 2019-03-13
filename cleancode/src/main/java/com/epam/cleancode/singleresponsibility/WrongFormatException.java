package com.epam.cleancode.singleresponsibility;

class WrongFormatException extends RuntimeException {
    private String message;

    WrongFormatException(String error) {
        super();
        this.message = error;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

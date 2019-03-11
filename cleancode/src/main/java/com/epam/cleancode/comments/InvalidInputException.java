package com.epam.cleancode.comments;

public class InvalidInputException extends RuntimeException {
    String message;

    public InvalidInputException(String s) {
        super();

        this.message = messageText;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

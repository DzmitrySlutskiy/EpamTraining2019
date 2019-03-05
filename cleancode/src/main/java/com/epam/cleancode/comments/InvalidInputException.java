package com.epam.cleancode.comments;

public class InvalidInputException extends RuntimeException{
    String message;
    public InvalidInputException(String Error) {
        super();
        this.message = Error;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

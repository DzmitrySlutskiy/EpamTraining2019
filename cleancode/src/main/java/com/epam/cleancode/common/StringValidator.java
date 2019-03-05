package com.epam.cleancode.common;

import com.epam.cleancode.exceptions.WrongFormatException;

public class StringValidator {
    private static StringValidator INSTANCE;

    private StringValidator() {

    }

    public static StringValidator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StringValidator();
        }
        return INSTANCE;
    }

    public void validate(String numbers) {
        if (numbers == null || isNotDigits(numbers)) throw new WrongFormatException();
    }

    private boolean isNotDigits(String numbers) {
        return !isDigits(numbers);
    }

    private boolean isDigits(String numbers) {
        return numbers.matches(Constants.NUMBERS_REGEXP);
    }

    public boolean isNotEmpty(String numbers) {
        return !numbers.isEmpty();
    }
}

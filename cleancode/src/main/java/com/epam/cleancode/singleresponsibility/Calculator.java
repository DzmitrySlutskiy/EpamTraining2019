package com.epam.cleancode.singleresponsibility;

public class Calculator {
    private enum Operation {
        SUM, MUL
    }

    private static final String SEPARATOR = ",";
    private static final String REGEX_FOR_SEARCH_DIGITS = "[\\d,]*";

    public String add(String numbers) {
        return "sum: " + String.valueOf(getResultOperation(numbers, Operation.SUM));
    }

    public String multiply(String numbers) {
        return "product: " + String.valueOf(getResultOperation(numbers, Operation.MUL));
    }

    private int getResultOperation(String numbers, Operation operation) {
        int result = operation == Operation.MUL ? 1 : 0;

        validate(numbers);

        for (String s : numbers.split(SEPARATOR)) {
            if (!s.isEmpty()) {
                switch (operation) {
                    case SUM:
                        result += Integer.valueOf(s);

                        break;
                    case MUL:
                        result *= Integer.valueOf(s);

                        break;
                }
            }
        }

        return result;
    }

    private void validate(String numbers) {
        if (numbers == null || !isDigits(numbers)) {
            throw new WrongFormatException();
        }
    }

    private boolean isDigits(String numbers) {
        return numbers.matches(REGEX_FOR_SEARCH_DIGITS);
    }
}

package com.epam.cleancode.singleresponsibility;

public class Calculator {
    private enum Operation {
        SUM, MUL
    }

    private static final String SEPARATOR = ",";

    public String add(String numbers) {
        return "sum: " + String.valueOf(getResultOperation(numbers, Operation.SUM));
    }

    public String multiply(String numbers) {
        return "product: " + String.valueOf(getResultOperation(numbers, Operation.MUL));
    }

    private int getResultOperation(String numbers, Operation operation) {
        int Result = operation == Operation.MUL ? 1 : 0;

        validate(numbers);

        for (String s : numbers.split(SEPARATOR)) {
            if (!s.isEmpty()) {
                switch (operation) {
                    case SUM:
                        Result += Integer.valueOf(s);

                        break;
                    case MUL:
                        Result *= Integer.valueOf(s);

                        break;
                }
            }
        }

        return Result;
    }

    private void validate(String numbers) {
        if (numbers == null || !isDigits(numbers)) {
            throw new WrongFormatException();
        }
    }

    private boolean isDigits(String numbers) {
        return numbers.matches("[\\d" + SEPARATOR + "]*");
    }
}

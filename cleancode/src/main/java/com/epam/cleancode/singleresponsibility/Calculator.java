package com.epam.cleancode.singleresponsibility;

public class Calculator {

    private static final String SEPARATOR = ",";
    private static final String REGULAR_EXPRESSION = "[\\d" + SEPARATOR + "]*";
    private static final String MESSAGE_BAD_INPUT_PARAMETER = "Only digits are allowed!";

    public String add(String numbers) {
        validate(numbers);

        return "sum: " + getSum(numbers);
    }

    private int getSum(String numbers) {
        int sum = 0;
        for (String number : numbers.split(SEPARATOR)) {
            if (!number.isEmpty()) {
                sum += Integer.valueOf(number);
            }
        }

        return sum;
    }

    public String multiply(String numbers) {
        validate(numbers);

        return "product: " + getProduct(numbers);
    }

    private int getProduct(String numbers) {
        int sum = 1;
        for (String number : numbers.split(SEPARATOR)) {
            if (!number.isEmpty()) {
                sum *= Integer.valueOf(number);
            }
        }

        return sum;
    }

    private void validate(String numbers) {
        if (numbers == null || !isDigits(numbers)) {
            throw new WrongFormatException(MESSAGE_BAD_INPUT_PARAMETER);
        }
    }

    private boolean isDigits(String numbers) {
        return numbers.matches(REGULAR_EXPRESSION);
    }
}

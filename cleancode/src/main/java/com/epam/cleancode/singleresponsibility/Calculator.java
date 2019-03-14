package com.epam.cleancode.singleresponsibility;

public class Calculator {

    private static final String SEPARATOR = ",";
    private static final String VALIDATION_EXCEPTION_MESSAGE = "Only digits allowed";
    private static final String REGEX_FOR_IS_DIGITS_MATCHING = "[\\d" + SEPARATOR + "]*";

    public String add(final String numbers) {
        validate(numbers);

        return "sum: " + String.valueOf(getSum(numbers));
    }

    public String multiply(final String numbers) {
        validate(numbers);

        return "product: " + String.valueOf(getProduct(numbers));
    }

    private int getSum(final String numbers) {
        int sum = 0;

        for (String s : numbers.split(SEPARATOR)) {

            if (isNotEmpty(s)) {
                sum += Integer.valueOf(s);
            }

        }

        return sum;
    }

    private int getProduct(final String numbers) {
        int sum = 1;

        for (String s : numbers.split(SEPARATOR)) {

            if (isNotEmpty(s)) {
                sum *= Integer.valueOf(s);
            }

        }

        return sum;
    }

    private boolean isNotEmpty(final String numbers) {
        return !numbers.isEmpty();
    }

    private void validate(final String numbers) {

        if (numbers == null || isNotDigits(numbers)) {
            throw new WrongFormatException(VALIDATION_EXCEPTION_MESSAGE);
        }

    }

    private boolean isNotDigits(final String numbers) {
        return !isDigits(numbers);
    }

    private boolean isDigits(final String numbers) {
        return numbers.matches(REGEX_FOR_IS_DIGITS_MATCHING);
    }

}

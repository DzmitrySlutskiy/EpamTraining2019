package com.epam.cleancode.singleresponsibility;

class Calculator {

    private static final String SEPARATOR = ",";
    private static final String REGEX_FOR_CHECK_ON_DIGITS = "[\\d" + SEPARATOR + "]*";
    private static final String WRONG_FORMAT_EXCEPTION = "letters are not allowed";

    String add(String numbers) {
        validate(numbers);

        return "sum: " + getSum(numbers);
    }

    String multiply(String numbers) {
        validate(numbers);

        return "product: " + getProduct(numbers);
    }

    private int getSum(String numbers) {
        int sum = 0;

        for (String s : numbers.split(SEPARATOR)) {
            if (isNotEmpty(s)) {
                sum += Integer.valueOf(s);
            }
        }

        return sum;
    }

    private int getProduct(String numbers) {
        int sum = 1;

        for (String s : numbers.split(SEPARATOR)) {
            if (isNotEmpty(s)) {
                sum *= Integer.valueOf(s);
            }
        }

        return sum;
    }

    private boolean isNotEmpty(String numbers) {
        return !numbers.isEmpty();
    }

    private void validate(String numbers) {
        if (numbers == null || !isDigits(numbers)) {
            throw new WrongFormatException(WRONG_FORMAT_EXCEPTION);
        }
    }

    private boolean isDigits(String numbers) {
        return numbers.matches(REGEX_FOR_CHECK_ON_DIGITS);
    }

}

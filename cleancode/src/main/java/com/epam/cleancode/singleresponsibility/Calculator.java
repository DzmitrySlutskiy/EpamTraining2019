package com.epam.cleancode.singleresponsibility;

public class Calculator {

    private static final String REGEX_NUMBERS_ROW = "[\\d,]*";
    private static final String SEPARATOR = ",";

    public String add(String numbers) {
        validate(numbers);

        return "sum: " + getSum(numbers);
    }

    public String multiply(String numbers) {
        validate(numbers);

        return "product: " + getProduct(numbers);
    }

    private int getSum(String numbers) {
        int result = 0;

        for (String number : numbers.split(SEPARATOR)) {
            if (!isEmpty(number)) {
                result += Integer.valueOf(number);
            }
        }

        return result;
    }

    private int getProduct(String numbers) {
        //start initialization for correct multiply
        int result = 1;

        for (String number : numbers.split(SEPARATOR)) {
            if (!isEmpty(number)) {
                result *= Integer.valueOf(number);
            }
        }

        return result;
    }

    private void validate(String numbers) {
        if (numbers == null || !isDigits(numbers)) {
            throw new WrongFormatException("Wrong format");
        }
    }

    private boolean isDigits(String numbers) {
        return numbers.matches(REGEX_NUMBERS_ROW);
    }

    private boolean isEmpty(String numbers) {
        return numbers.isEmpty();
    }

}

package com.epam.cleancode.singleresponsibility;

public class Calculator{

    private static final String SEPARATOR = ",";

    public String add(String numbers) {
        validate(numbers);

        return "sum: " +getSum(numbers);
    }

    public String multiply(String numbers) {
        validate(numbers);

        return "product: " + getProduct(numbers);
    }

    private int getSum(String numbers) {
        int sum = 0;
        for (String number : numbers.split(SEPARATOR)) {
            if (isNotEmpty(number)) {
                sum += Integer.valueOf(number);
            }
        }

        return sum;
    }

    private int getProduct(String numbers) {
        int sum = 1;
        for (String number : numbers.split(SEPARATOR)) {
            if (isNotEmpty(number)) {
                sum *= Integer.valueOf(number);
            }
        }

        return sum;
    }

    private boolean isNotEmpty(String numbers) {
        return !numbers.isEmpty();
    }

    private void validate(String numbers) {
        if (numbers == null ||
                !numbers.matches("[\\d" + SEPARATOR + "]*")) {
            throw new WrongFormatException("Wrong format exception");
        }
    }
}

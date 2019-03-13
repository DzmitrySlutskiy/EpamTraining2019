package com.epam.cleancode.singleresponsibility;

class Calculator {

    private static final String SEPARATOR = ",";

    String add(String numbers) {
        validate(numbers);

        return "sum: " + String.valueOf(getSum(numbers));
    }

    String multiply(String numbers) {
        validate(numbers);

        return "product: " + String.valueOf(getProduct(numbers));
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
        if (numbers == null || isNotDigits(numbers)) {
            throw new WrongFormatException();
        }
    }

    private boolean isNotDigits(String numbers) {
        return !isDigits(numbers);
    }

    private boolean isDigits(String numbers) {
        return numbers.matches("[\\d" + SEPARATOR + "]*");
    }

}

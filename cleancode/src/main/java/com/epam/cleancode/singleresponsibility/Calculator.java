package com.epam.cleancode.singleresponsibility;

public class Calculator {

    private static final String SEPARATOR = ",";
    private static final String REGEX = "[\\d" + SEPARATOR + "]*";
    private static final String SUM = "sum: ";
    private static final String PRODUCT = "product: ";

    public String add(final String numbers) {
        validate(numbers);
        final String result = String.valueOf(getSum(numbers));

        return SUM.concat(result);
    }

    public String multiply(final String numbers) {
        validate(numbers);
        final String result = String.valueOf(getProduct(numbers));

        return PRODUCT.concat(result);
    }

    private int getSum(final String numbers) {
        int sum = 0;

        for (final String number : numbers.split(SEPARATOR)) {
            if (isNotEmpty(number)) {
                sum += Integer.valueOf(number);
            }
        }

        return sum;
    }

    private int getProduct(final String numbers) {
        int sum = 1;

        for (final String number : numbers.split(SEPARATOR))
            if (isNotEmpty(number)) {
                sum *= Integer.valueOf(number);
            }

        return sum;
    }

    private boolean isNotEmpty(final String numbers) {
        return !numbers.isEmpty();
    }

    private void validate(final String numbers) {
        if (numbers == null || isNotDigits(numbers))
            throw new WrongFormatException();
    }

    private boolean isNotDigits(final String numbers) {
        return !isDigits(numbers);
    }

    private boolean isDigits(final String numbers) {
        return numbers.matches(REGEX);
    }

}

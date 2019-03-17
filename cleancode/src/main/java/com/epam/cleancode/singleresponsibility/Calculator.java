package com.epam.cleancode.singleresponsibility;

public class Calculator {

    private static final String SEPARATOR = ",";
    private static final int ZERO = 0;
    private static final int ONE = 1;

    public String add(String numbers) {
        validate(numbers);
        return "sum: " + String.valueOf(getSum(numbers));
    }

    public String multiply(String numbers) {
        validate(numbers);
        return "product: " + String.valueOf(getProduct(numbers));
    }

    private int getSum(String numbers) {
        int sum = ZERO;
        for (String s : numbers.split(SEPARATOR)) if (isNotEmpty(s)) sum += Integer.valueOf(s);
        return sum;
    }

    private int getProduct(String numbers) {
        int sum = ONE;
        for (String s : numbers.split(SEPARATOR))
            if (isNotEmpty(s))
                sum *= Integer.valueOf(s);
        return sum;
    }

    private boolean isNotEmpty(String numbers) {
        return !numbers.isEmpty();
    }

    private void validate(String numbers) {
        if (numbers == null || isNotDigits(numbers))
            throw new WrongFormatException();
    }

    private boolean isNotDigits(String numbers) {
        return !isDigits(numbers);
    }

    private boolean isDigits(String numbers) {
        return numbers.matches("[\\d" + SEPARATOR + "]*");
    }

}

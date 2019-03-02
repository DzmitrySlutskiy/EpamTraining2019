package com.epam.cleancode.singleresponsibility;

import org.jetbrains.annotations.NotNull;

public class Calculator {

    private static final String SEPARATOR = ",";
    private static final String RESULT_ADDITION = "sum: ";
    private static final String RESULT_MULTIPLY = "product: ";
    private static final String REGEX_COMBINATION = "[\\d,]*";

    public String addition(String inputStringOfNumbers) {
        checkValidity(inputStringOfNumbers);
        return RESULT_ADDITION + getSum(inputStringOfNumbers);
    }

    public String multiply(String inputStringOfNumbers) {
        checkValidity(inputStringOfNumbers);
        return RESULT_MULTIPLY + getProduct(inputStringOfNumbers);
    }

    private int getSum(@NotNull String inputStringOfNumbers) {
        int amount = 0;
        for (String symbol : inputStringOfNumbers.split(SEPARATOR)) {
            if (!symbol.isEmpty()) {
                amount += Integer.valueOf(symbol);
            }
        }
        return amount;
    }

    private int getProduct(@NotNull String inputStringOfNumbers) {
        int product = 1;
        for (String symbol : inputStringOfNumbers.split(SEPARATOR))
            if (!symbol.isEmpty()) {
                product *= Integer.valueOf(symbol);
            }
        return product;
    }

    private void checkValidity(String inputStringOfNumbers) {
        if (inputStringOfNumbers == null || isNotDigits(inputStringOfNumbers)) {
            throw new WrongFormatException();
        }
    }

    private boolean isNotDigits(@NotNull String inputStringOfNumbers) {
        return !inputStringOfNumbers.matches(REGEX_COMBINATION);
    }
}
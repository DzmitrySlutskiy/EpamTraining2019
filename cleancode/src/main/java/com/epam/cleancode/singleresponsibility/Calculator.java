package com.epam.cleancode.singleresponsibility;

import com.epam.cleancode.common.Constants;
import com.epam.cleancode.common.StringValidator;
import com.epam.cleancode.exceptions.WrongFormatException;

class Calculator {
    private StringValidator validator = StringValidator.getInstance();

    String add(String numbers) throws WrongFormatException {
        validator.validate(numbers);
        return makeResult(Constants.SUM_PREFIX, perform(0,
                numbers.split(Constants.SEPARATOR),
                Operator.ADDITION));
    }

    String multiply(String numbers) throws WrongFormatException {
        validator.validate(numbers);
        return makeResult(Constants.PRODUCT_PREFIX, perform(1,
                numbers.split(Constants.SEPARATOR),
                Operator.MULTIPLICATION));
    }

    private int perform(int initialValue, String[] numbers, Operator operator) {
        int result = initialValue;
        for (String number : numbers) {
            if (validator.isNotEmpty(number))
                result = operator.apply(result, Integer.valueOf(number));
        }
        return result;
    }

    private String makeResult(String prefix, int result) {
        StringBuilder builder = new StringBuilder();
        builder.append(prefix);
        builder.append(result);
        return builder.toString();
    }
}

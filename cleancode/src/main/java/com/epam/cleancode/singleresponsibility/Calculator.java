package com.epam.cleancode.singleresponsibility;

class Calculator{

    private static final String SEPARATOR = ",";
    private static final String DIGITREGEX = "[\\d,]*";

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

        for (String separatedPart : numbers.split(SEPARATOR)){

            if (isNotEmpty(separatedPart)){
                sum += Integer.valueOf(separatedPart);
            }
        }

        return sum;
    }

    private int getProduct(String numbers) {

        int sum = 1;

        for (String separatedPart : numbers.split(SEPARATOR))
            if (isNotEmpty(separatedPart))
                sum *= Integer.valueOf(separatedPart);
        return sum;
    }

    private boolean isNotEmpty(String numbers) {

        return !numbers.isEmpty();
    }

    private void validate(String numbers) {

        if (numbers == null || isNotDigits(numbers)) {
            throw new WrongFormatException("Only digits supported");
        }
    }

    private boolean isNotDigits(String numbers) {

        return !isDigits(numbers);
    }

    private boolean isDigits(String numbers) {

        return numbers.matches(DIGITREGEX);
    }

}

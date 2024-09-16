package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    private static final String MESSAGE_BAD_INPUT_PARAMETERS = "Negative values are not allowed";
    private static final int AMOUNT_MONTHS_IN_YEAR = 12;

    public static double calculateMonthlyPayment(int startAmount, int termInYears, double percent) {
        inputValidation(startAmount, termInYears, percent);
        double percentInYear = percent / 100.0;
        double termInMonth = termInYears * AMOUNT_MONTHS_IN_YEAR;

        if (percentInYear == 0) {
            return startAmount / termInMonth;
        }

        return monthlyPayment(startAmount, percentInYear, termInMonth);
    }

    private static void inputValidation(int startAmount, int termInYears, double percent) {
        if (haveValidParameters(startAmount, termInYears, percent)) {
            throw new InvalidInputException(MESSAGE_BAD_INPUT_PARAMETERS);
        }
    }

    private static boolean haveValidParameters(int startAmount, int termInYears, double percent) {
        return startAmount < 0 || termInYears <= 0 || percent < 0;
    }

    private static double monthlyPayment(int startAmount, double percentInYear) {
        return (startAmount * percentInYear / AMOUNT_MONTHS_IN_YEAR) /
                (1 - Math.pow(1 + percentInYear / AMOUNT_MONTHS_IN_YEAR, -termInMonth));
    }
}

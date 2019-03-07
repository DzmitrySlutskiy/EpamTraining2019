package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    private static final String MESSAGE_BAD_INPUT_PARAMETERS = "Negative values are not allowed";
    private static final int AMOUNT_MONTHS_IN_YEAR = 12;

    /**
     * @param startAmount principal amount
     * @param termInYears term of mortgage in years
     * @param percent     rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(int startAmount, int termInYears, double percent) {
        if (haveValidParameters(startAmount, termInYears, percent)) {
            throw new InvalidInputException(MESSAGE_BAD_INPUT_PARAMETERS);
        }
        double percentInYear = percent / 100.0;
        double termInMonth = termInYears * AMOUNT_MONTHS_IN_YEAR;
        if (percentInYear == 0) {
            return startAmount / termInMonth;
        }

        return monthlyPayment(startAmount, percentInYear, termInMonth);
    }

    private static boolean haveValidParameters(int startAmount, int termInYears, double percent) {
        return startAmount < 0 || termInYears <= 0 || percent < 0;
    }

    private static double monthlyPayment(int startAmount, double percentInYear,
                                         double termInMonth) {
        return (startAmount * percentInYear / 12.0) /
                (1 - Math.pow(1 + percentInYear / 12.0, -termInMonth));
    }
}

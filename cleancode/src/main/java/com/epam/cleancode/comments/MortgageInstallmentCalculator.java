package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    private static final String MESSAGE_BAD_INPUT_PARAMETERS = "Negative values are not allowed";

    /**
     * @param startAmount principal amount
     * @param termInYears term of mortgage in years
     * @param percent rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(int startAmount, int termInYears, double percent) {
        if (startAmount < 0 || termInYears <= 0 || percent < 0) {
            throw new InvalidInputException(MESSAGE_BAD_INPUT_PARAMETERS);
        }
        double percentInYear = percent / 100.0;
        double termInMonth = termInYears * 12;
        if (percentInYear == 0) {
            return startAmount / termInMonth;
        }
        double percentInMonth = percentInYear / 12.0;
        double monthlyPayment = (startAmount * percentInMonth) /
                (1 - Math.pow(1 + percentInMonth, -termInMonth));

        return monthlyPayment;
    }
}

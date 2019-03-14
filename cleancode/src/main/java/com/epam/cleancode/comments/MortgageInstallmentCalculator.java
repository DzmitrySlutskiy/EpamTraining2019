package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    private static final double ONE_HUNDRED = 100.0;
    private static final int MONTHS_IN_YEAR = 12;
    private static final String NEGATIVE_VALUES_EXCEPTION_MESSAGE = "Negative values are not allowed";

    /**
     * @param principalAmount     principal amount
     * @param mortgageTermInYears term of mortgage in years
     * @param interestRate        rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(final int principalAmount, final int mortgageTermInYears, final double interestRate) {
        double decimalInterestRate = interestRate / ONE_HUNDRED;
        double termInMonths = mortgageTermInYears * MONTHS_IN_YEAR;
        double monthlyRate = decimalInterestRate / MONTHS_IN_YEAR;

        if (principalAmount < 0 || mortgageTermInYears <= 0 || interestRate < 0) {
            throw new InvalidInputException(NEGATIVE_VALUES_EXCEPTION_MESSAGE);
        }

        if (decimalInterestRate == 0) {
            return principalAmount / termInMonths;
        }

        return (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
    }


}

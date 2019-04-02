package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    private static final double ONE_HUNDRED = 100.0;
    private static final int MONTHS_IN_YEAR = 12;
    private static final String NEGATIVE_VALUES_EXCEPTION_MESSAGE = "Negative values are not allowed";

    public static double calculateMonthlyPayment(final int principalAmount, final int mortgageTermInYears, final double interestRate) {
        double decimalInterestRate = interestRate / ONE_HUNDRED;
        double termInMonths = mortgageTermInYears * MONTHS_IN_YEAR;
        double monthlyRate = decimalInterestRate / MONTHS_IN_YEAR;

        if (isValuesNegative(principalAmount,mortgageTermInYears,interestRate)) {
            throw new InvalidInputException(NEGATIVE_VALUES_EXCEPTION_MESSAGE);
        }

        if (decimalInterestRate == 0) {
            return principalAmount / termInMonths;
        }

        return computePayment(principalAmount,monthlyRate,interestRate);
    }

    private static boolean isValuesNegative(final double principalAmount, final double mortgageTermInYears, final double interestRate) {
        return principalAmount < 0 || mortgageTermInYears <= 0 || interestRate < 0;
    }

    private static double computePayment(final double principalAmount, final double monthlyRate, final double termInMonths){
        return (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
    }


}

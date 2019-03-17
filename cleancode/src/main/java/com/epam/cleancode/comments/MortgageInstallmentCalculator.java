package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    private static final String NEGATIVE_VALUES_ARE_NOT_ALLOWED = "Negative values are not allowed";
    private static final double TWELVE = 12.0;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int ONE_HUNDRED = 100;

    public static double calculateMonthlyPayment(final int principalAmount,
                                                 final int mortgageTermInYears,
                                                 final double interestRateInPercents) {
        validateMortgageParameters(principalAmount, mortgageTermInYears, interestRateInPercents);

        final double monthlyRate = getMonthlyRate(interestRateInPercents);
        final double mortgageTermInMonths = getMonthsAmount(mortgageTermInYears);

        return getMonthlyPayment(principalAmount, mortgageTermInMonths, monthlyRate);
    }

    private static void validateMortgageParameters(final int principalAmount,
                                                   final int mortgageTermInYears,
                                                   final double interestRate) {
        if (principalAmount < ZERO
                || mortgageTermInYears <= ZERO
                || interestRate < ZERO) {
            throw new InvalidInputException(NEGATIVE_VALUES_ARE_NOT_ALLOWED);
        }
    }

    private static double getMonthlyRate(final double interestRate) {
        return interestRate / ONE_HUNDRED / TWELVE;
    }

    private static double getMonthsAmount(final int yearsAmount) {
        return yearsAmount * TWELVE;
    }

    private static double getMonthlyPayment(final int principalAmount,
                                            final double mortgageTermInMonths,
                                            final double monthlyRate) {
        return monthlyRate == ZERO
                ? principalAmount / mortgageTermInMonths
                : (principalAmount * monthlyRate)
                / (ONE - Math.pow(ONE + monthlyRate, -ONE * mortgageTermInMonths));
    }
}

package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    private static final String NEGATIVE_VALUES_ARE_NOT_ALLOWED = "Negative values are not allowed";
    private static final int numberOfMonthsInYear = 12;

    public static double calculateMonthlyPayment(int principalAmount, int termOfMortgageInYears, double rateOfInterest) {
        double termInMonths = termOfMortgageInYears * numberOfMonthsInYear;
        double monthlyRate = (rateOfInterest / 100) / numberOfMonthsInYear;

        validate(principalAmount, termOfMortgageInYears, rateOfInterest);

        if (rateOfInterest == 0) {
            return principalAmount / termInMonths;
        }

        double monthlyPayment = (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
        return monthlyPayment;
    }

    private static void validate(int principalAmount, int termOfMortgageInYears, double rateOfInterest) {
        if (principalAmount < 0 || termOfMortgageInYears <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException(NEGATIVE_VALUES_ARE_NOT_ALLOWED);
        }
    }
}

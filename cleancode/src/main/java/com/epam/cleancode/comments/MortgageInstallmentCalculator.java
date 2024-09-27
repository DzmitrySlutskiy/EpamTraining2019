package com.epam.cleancode.comments;

class MortgageInstallmentCalculator {
    private static final Double MONTHS_NUMBER = 12d;
    private static final Double ZERO_RATE_OF_INTEREST = 0d;
    private static final Double INTEREST_RATE_TO_DECIMAL = 100.0;

    private MortgageInstallmentCalculator() {}

    static double calculateMonthlyPayment(
            int principalAmount, int termOfMortgageYears, double rateOfInterest) {

        inputValidation(principalAmount, termOfMortgageYears, rateOfInterest);

        // Convert interest rate into a decimal - eg. 6.5% = 0.065
        rateOfInterest /= INTEREST_RATE_TO_DECIMAL;

        double termInYears = termOfMortgageYears * MONTHS_NUMBER;

        if (rateOfInterest == ZERO_RATE_OF_INTEREST) {
            return principalAmount / termInYears;
        }

        double monthlyRate = rateOfInterest / MONTHS_NUMBER;

        Double monthlyPayment = (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInYears));

        return monthlyPayment;
    }

    static void inputValidation(int principalAmount, int termOfMortgageYears, double rateOfInterest) {
        if (principalAmount < 0 || termOfMortgageYears <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }
    }
}

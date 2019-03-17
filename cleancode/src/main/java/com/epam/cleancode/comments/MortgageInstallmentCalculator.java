package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final double HUNDRED = 100.0;
    private static final double TWELVE = 12.0;

    public static double calculateMonthlyPayment(
            int principalAmount, int termOfMortgage, double rateOfInterest) {

        if (principalAmount < ZERO || termOfMortgage <= ZERO || rateOfInterest < ZERO) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        rateOfInterest /= HUNDRED;
        double time = termOfMortgage * TWELVE;

        if (rateOfInterest == ZERO) {
            return principalAmount / time;
        }

        double monthlyRate = rateOfInterest / TWELVE;
        double monthlyPayment = (principalAmount * monthlyRate) / (ONE - Math.pow(ONE + monthlyRate, -time));

        return monthlyPayment;
    }
}

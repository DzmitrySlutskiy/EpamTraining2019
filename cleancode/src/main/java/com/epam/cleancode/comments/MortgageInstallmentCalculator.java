package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    private static final int MONTHS_NUMBER = 12;

    public static double calculateMonthlyPayment(int principal, int mortgageTermInYears, double rateOfInterest) {
        if (principal < 0 || mortgageTermInYears <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        rateOfInterest /= 100.0;
        double mortgageTermInMonths = mortgageTermInYears * MONTHS_NUMBER;
        double monthlyRateOfInterest = rateOfInterest / MONTHS_NUMBER;

        if (rateOfInterest == 0) {
            return principal / mortgageTermInMonths;
        }

        double monthlyPercentageAtPrincipal = monthlyRateOfInterest / (1 - Math.pow(1 + monthlyRateOfInterest, -mortgageTermInMonths));
        double monthlyPayment = principal * monthlyPercentageAtPrincipal;

        return monthlyPayment;
    }
}
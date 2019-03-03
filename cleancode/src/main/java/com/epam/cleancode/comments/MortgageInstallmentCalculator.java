package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    public static double calculateMonthlyPayment(int principal, int mortgageTermInYears, double rateOfInterest) {
        if (principal < 0 || mortgageTermInYears <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        rateOfInterest /= 100.0;
        double mortgageTermInMonths = mortgageTermInYears * 12;
        double monthlyRateOfInterest = rateOfInterest / 12.0;

        if (rateOfInterest == 0) {
            return principal / mortgageTermInMonths;
        }

        return (principal * monthlyRateOfInterest) / (1 - Math.pow(1 + monthlyRateOfInterest, -mortgageTermInMonths));
    }
}
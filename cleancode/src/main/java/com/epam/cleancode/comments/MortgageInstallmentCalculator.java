package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    public static double calculateMonthlyPayment(int principalAmount, int termMortgageInYears, double rateOfInterest) {
        double termMortgageInMonths;
        double monthlyRateOfInterest;
        double monthlyPayment;

        if (principalAmount < 0 || termMortgageInYears <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        rateOfInterest /= 100.0;
        termMortgageInMonths = termMortgageInYears * 12;

        if (rateOfInterest == 0) {
            return principalAmount / termMortgageInMonths;
        }

        monthlyRateOfInterest = rateOfInterest / 12.0;
        monthlyPayment = (principalAmount * monthlyRateOfInterest) / (1 - Math.pow(1 + monthlyRateOfInterest, -termMortgageInMonths));

        return monthlyPayment;
    }
}

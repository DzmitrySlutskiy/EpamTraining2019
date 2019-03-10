package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    public static double calculateMonthlyPayment(
            int principalAmount, int termOfMortgage, double rateOfInterest) {

        if (principalAmount < 0 || termOfMortgage <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }
        rateOfInterest /= 100.0;
        double time = termOfMortgage * 12;

        if (rateOfInterest == 0) {
            return principalAmount / time;
        }
        double m = rateOfInterest / 12.0;
        double monthlyPayment = (principalAmount * m) / (1 - Math.pow(1 + m, -time));

        return monthlyPayment;
    }
}

package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {
    public static double calculateMonthlyPayment(int loanAmount, int termDuration,
                                                 double percentBasedRateOfInterest) {

        if (loanAmount < 0 || termDuration <= 0 || percentBasedRateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        double decimalRateOfInterest = percentBasedRateOfInterest / 100.0;
        double termDurationInMonths = termDuration * 12.0;

        if(decimalRateOfInterest==0) {
            return loanAmount / termDurationInMonths;
        }

        double monthlyRate = decimalRateOfInterest / 12.0;

        return (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termDurationInMonths));
    }
}

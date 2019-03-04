package com.epam.cleancode.comments;

class MortgageInstallmentCalculator {

    private MortgageInstallmentCalculator() {}

    static double calculateMonthlyPayment(
            int principalAmount, int termOfMortgageYears, double rateOfInterest) {

        //cannot have negative loanAmount, term duration and rate of interest
        if (principalAmount < 0 || termOfMortgageYears <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        // Convert interest rate into a decimal - eg. 6.5% = 0.065
        rateOfInterest /= 100.0;

        // convert term in years to term in months
        double termInYears = termOfMortgageYears * 12d;

        //for zero interest rates
        if (rateOfInterest == 0) {
            return principalAmount / termInYears;
        }
        // convert into monthly rate
        double monthlyRate = rateOfInterest / 12.0;

        // Calculate the monthly payment
        // The Math.pow() method is used calculate values raised to a power
        return (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInYears));
    }
}

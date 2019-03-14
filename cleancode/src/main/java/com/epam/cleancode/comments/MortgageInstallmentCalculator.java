package com.epam.cleancode.comments;

class MortgageInstallmentCalculator {

    private static final int monthsInYear = 12;

    static double calculateMonthlyPayment
            (int principalAmount, int mortgageTermInYears, double interestRate) {

        if (principalAmount < 0
                || interestRate < 0
                || mortgageTermInYears <= 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        interestRate /= 100.0;
        double mortgageTermInMonths = mortgageTermInYears * monthsInYear;

        if(interestRate == 0) {
            return principalAmount / mortgageTermInMonths;
        }

        double monthlyRate = interestRate / monthsInYear;

        double niceOperandName1 = (principalAmount * monthlyRate);
        double niceOperandName2 = (1 - Math.pow(1 + monthlyRate, -mortgageTermInMonths));

        double monthlyPaymentAmount = niceOperandName1 / niceOperandName2;

        return monthlyPaymentAmount;
    }
}

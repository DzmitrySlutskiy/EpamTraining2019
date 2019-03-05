package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {


    public static double calculateMonthlyPayment(
            int PrincipalAmount,
            int TermOfMortageInYears,
            double RateOfInterest) {

        if (PrincipalAmount < 0 || TermOfMortageInYears <= 0 || RateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        RateOfInterest /= 100.0;

        double TermInMonth = TermOfMortageInYears * 12;

        if(RateOfInterest==0)
            return  PrincipalAmount/TermInMonth;
        double MonthlyRate = RateOfInterest / 12.0;
        double monthlyPayment = (PrincipalAmount * MonthlyRate) / (1 - Math.pow(1 + MonthlyRate, -TermInMonth));
        return monthlyPayment;
    }
}

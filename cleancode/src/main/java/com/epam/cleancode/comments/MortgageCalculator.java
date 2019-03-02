package com.epam.cleancode.comments;

public class MortgageCalculator {

    private static final double NUMBER_OF_MONTHS = 12.0;

    public static double calculateMonthlyPayment(int principalAmount, int mortageTerm, double rate) {
        if (principalAmount < 0 || mortageTerm <= 0 || rate < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        } else if(rate==0) {
            return (double) principalAmount / (mortageTerm * NUMBER_OF_MONTHS);
        } else {

            return (principalAmount * (rate/ NUMBER_OF_MONTHS)/100) /
                    (1 - Math.pow(1 + (rate/NUMBER_OF_MONTHS)/100, -(mortageTerm * NUMBER_OF_MONTHS)));
        }
    }
}

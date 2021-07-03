package com.epam.cleancode.comments;

public class MortgageCalculator {

    private static final double NUMBER_OF_MONTHS = 12.0;

    public static double calculateMonthlyPayment(int principalAmount, int mortageTerm, double rate) {
        double mounthlyRate = rate / NUMBER_OF_MONTHS / 100;
        if (principalAmount < 0 || mortageTerm <= 0 || rate < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        } else if (rate == 0) {
            return monthlyPaymentWithZeroRates(principalAmount, mortageTerm);
        } else {
            return montlyPayment(principalAmount, mounthlyRate, mortageTerm);
        }
    }

    private static double monthlyPaymentWithZeroRates(int principalAmount, int mortageTerm) {
        return principalAmount / (mortageTerm * NUMBER_OF_MONTHS);
    }

    private static double montlyPayment(int principalAmount, double mounthlyRate, int mortageTerm) {
        return principalAmount * mounthlyRate /
                (1 - Math.pow(1 + mounthlyRate, -(mortageTerm * NUMBER_OF_MONTHS)));
    }
}

package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    private static final String NEGATIVE_VALUES_ARE_NOT_ALLOWED = "Negative values are not allowed";

    /**
     * @param principalAmount       principal amount
     * @param termOfMortgageInYears term of mortgage in years
     * @param rateOfInterest        rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(int principalAmount, int termOfMortgageInYears, double rateOfInterest) {
        double termInMonths = termOfMortgageInYears * 12;
        double monthlyRate = (rateOfInterest / 100) / 12.0;

        if (principalAmount < 0 || termOfMortgageInYears <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException(NEGATIVE_VALUES_ARE_NOT_ALLOWED);
        }

        if (rateOfInterest == 0) {
            return principalAmount / termInMonths;
        }

        return (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
    }
}

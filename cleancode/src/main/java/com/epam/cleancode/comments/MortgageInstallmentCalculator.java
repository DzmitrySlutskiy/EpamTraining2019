package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {
    private final static String NEGATIVE_VALUES_ARE_NOT_ALLOWED = "Negative values are not allowed";

    /**
     * @param principalAmount      principal amount
     * @param termOfMortgageInYear term of mortgage in years
     * @param rateOfInterest       rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(int principalAmount, int termOfMortgageInYear, double rateOfInterest) {

        checkValidity(principalAmount, termOfMortgageInYear, rateOfInterest);


        if (rateOfInterest / 100.0 == 0) {
            return principalAmount / (double) (12 * termOfMortgageInYear);
        }

        return getMonthlyPayment(principalAmount, rateOfInterest / 100.0 / 12.0, (double) 12 * termOfMortgageInYear);
    }

    private static void checkValidity(int principalAmount, int termOfMortgageInYear, double rateOfInterestInPercentage) {
        if (principalAmount < 0 || termOfMortgageInYear <= 0 || rateOfInterestInPercentage < 0) {
            throw new InvalidInputException(NEGATIVE_VALUES_ARE_NOT_ALLOWED);
        }
    }

    private static double getMonthlyPayment(int principalAmount, double monthlyRate, double termOfMortgageInMonths) {
        return (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termOfMortgageInMonths));
    }
}
package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {
    private final static String NEGATIVE_VALUES_ARE_NOT_ALLOWED = "Negative values are not allowed";
    private static double rateOfInterestInDecimal;
    private static double termOfMortgageInMonths;
    private static double monthlyRate;

    /**
     * @param principalAmount            principal amount
     * @param termOfMortgageInYear       term of mortgage in years
     * @param rateOfInterestInPercentage rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(int principalAmount, int termOfMortgageInYear, double rateOfInterestInPercentage) {

        checkValidity(principalAmount, termOfMortgageInYear, rateOfInterestInPercentage);

        rateOfInterestInDecimal = getRateOfInterestInDecimal(rateOfInterestInPercentage);
        termOfMortgageInMonths = getTermOfMortgageInMonths(termOfMortgageInYear);

        if (rateOfInterestInDecimal == 0) {
            return principalAmount / termOfMortgageInMonths;
        }

        monthlyRate = getMonthlyRate(rateOfInterestInDecimal);

        return getMonthlyPayment(principalAmount, monthlyRate, termOfMortgageInMonths);
    }

    private static double getTermOfMortgageInMonths(int termOfMortgageInYear) {
        return termOfMortgageInYear * 12;
    }

    private static void checkValidity(int principalAmount, int termOfMortgageInYear, double rateOfInterestInPercentage) {
        if (principalAmount < 0 || termOfMortgageInYear <= 0 || rateOfInterestInPercentage < 0) {
            throw new InvalidInputException(NEGATIVE_VALUES_ARE_NOT_ALLOWED);
        }
    }

    private static double getMonthlyRate(double rateOfInterestInDecimal) {
        return rateOfInterestInDecimal / 12.0;
    }

    private static double getRateOfInterestInDecimal(double rateOfInterestInPercentage) {
        return rateOfInterestInPercentage / 100.0;
    }

    private static double getMonthlyPayment(int principalAmount, double monthlyRate, double termOfMortgageInMonths) {
        return (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termOfMortgageInMonths));
    }
}
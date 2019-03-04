package com.epam.cleancode.comments;

class MortgageInstallmentCalculator {

    private static final String INVALID_INPUT_EXCEPTION = "Negative values are not allowed";
    private static final double NUMBER_OF_MONTHS = 12.0;

    MortgageInstallmentCalculator() {
    }

    /**
     * @param principalAmount       principal amount
     * @param termOfMortgageInYears term of mortgage in years
     * @param rateOfInterest        rate of interest
     * @return monthly payment amount
     */
    static double calculateMonthlyPayment(int principalAmount, int termOfMortgageInYears, double rateOfInterest) {
        double termInMonths = termOfMortgageInYears * NUMBER_OF_MONTHS;
        double rateInDecimal = rateOfInterest / 100.0;
        double monthlyRate = rateInDecimal / NUMBER_OF_MONTHS;

        checkValidation(principalAmount, termOfMortgageInYears, rateOfInterest);

        if (rateInDecimal == 0) {
            return principalAmount / termInMonths;
        }

        return (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
    }

    private static void checkValidation(int principalAmount, int termOfMortgageInYears, double rateOfInterest){
        if (principalAmount < 0 || termOfMortgageInYears <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException(INVALID_INPUT_EXCEPTION);
        }
    }
}

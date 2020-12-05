package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    private static final String INVALID_INPUT_EXCEPTION = "Negative values are not allowed";
    private static final double NUMBER_OF_MONTHS = 12.0;

    /**
     *
     * @param principalAmount principal amount
     * @param termOfMortgageInYears term of mortgage in years
     * @param rateOfInterest rate of interest
     * @return  monthly payment amount
     */

    static double calculateMonthlyPayment(int principalAmount, int termOfMortgageInYears, double rateOfInterest) {
        // Convert interest rate into a decimal - eg. 6.5% = 0.065
        rateOfInterest /= 100.0;

        // convert term in years to term in months
        double termInMonths = termOfMortgageInYears * NUMBER_OF_MONTHS ;

        //for zero interest rates
        if(rateOfInterest==0)
            return  principalAmount/termInMonths;

        // convert into monthly rate
        double monthlyRate = rateOfInterest / NUMBER_OF_MONTHS;
        // Calculate the monthly payment
        // The Math.pow() method is used calculate values raised to a power
        double monthlyPayment = (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
        checkValid(principalAmount, termOfMortgageInYears, rateOfInterest);

        return monthlyPayment;
    }
    private static void checkValid(int principalAmount, int termOfMortgageInYears, double rateOfInterest){
        //cannot have negative loanAmount, term duration and rate of interest
        if (principalAmount < 0 || termOfMortgageInYears <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException(INVALID_INPUT_EXCEPTION);
        }

    }
}

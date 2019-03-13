package com.epam.cleancode.comments;

class MortgageInstallmentCalculator {

    /**
     *
     * @param LoanAmount principal amount
     * @param TermDurationInYears term of mortgage in years
     * @param RateOfInterest rate of interest
     * @return monthly payment amount
     */
    static double calculateMonthlyPayment(int LoanAmount, int TermDurationInYears, double RateOfInterest) {

        //cannot have negative loanAmount, term duration and rate of interest
        if (LoanAmount < 0 || TermDurationInYears <= 0 || RateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        // Convert interest rate into a decimal - eg. 6.5% = 0.065
        RateOfInterest /= 100.0;

        // convert term in years to term in months
        double TermDurationInMonths = TermDurationInYears * 12;

        //for zero interest rates
        if(RateOfInterest==0)
            return  LoanAmount/TermDurationInMonths;

        // convert into monthly rate
        double RateOfMonthlyInterest = RateOfInterest / 12.0;

        // Calculate the monthly payment
        // The Math.pow() method is used calculate values raised to a power
        return (LoanAmount * RateOfMonthlyInterest) / (1 - Math.pow(1 + RateOfMonthlyInterest, -TermDurationInMonths));
    }
}

package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {

    /**
     *
     * @param p principal amount
     * @param t term of mortgage in years
     * @param r rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(
            int p, int t, double r) {

        //cannot have negative loanAmount, term duration and rate of interest
        if (p < 0 || t <= 0 || r < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        // Convert interest rate into a decimal - eg. 6.5% = 0.065
        r /= 100.0;

        // convert term in years to term in months
        double tim = t * 12;

        //for zero interest rates
        if(r==0)
            return  p/tim;

        // convert into monthly rate
        double m = r / 12.0;

        // Calculate the monthly payment
        // The Math.pow() method is used calculate values raised to a power
        double monthlyPayment = (p * m) / (1 - Math.pow(1 + m, -tim));

        return monthlyPayment;
    }
}

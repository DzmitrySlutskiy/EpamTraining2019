package com.epam.cleancode.comments;

public class MortgageInstallmentCalculator {
    
    public static double calculateMonthlyPayment(
            int principalAmount, int mortgageTerm, double rate) {

        if (principalAmount < 0 || mortgageTerm <= 0 || rate < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

       double decimalRate = rate / 100;

        double mortgageTermInMonths = mortgageTerm * NUMBER_OF_MONTHS;

        if(decimalRate == 0){
            return  principalAmount / mortgageTermInMonths;
        }

        double monthlyRate = decimalRate / NUMBER_OF_MONTHS;

        return (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -mortgageTermInMonths));
    }
}

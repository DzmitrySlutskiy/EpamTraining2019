package com.epam.cleancode.comments;

public class MortgageActivity {

    private MortgageActivity() {
        throw new IllegalStateException("MortgageActivity");
    }

    public static double calculateMonthlyPayment(int principalAmount, int termOfMortage, double rateOfInterest){
        if (principalAmount < 0 || termOfMortage <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        rateOfInterest = rateOfInterest / 100.0;
        double convertInMonths = termOfMortage * 12.0;

        if(rateOfInterest==0) {
            return principalAmount / convertInMonths;

        }

        double monthlyRate = rateOfInterest / 12.0;
        return (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, - convertInMonths));

    }
}

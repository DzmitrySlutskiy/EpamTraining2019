package com.epam.cleancode.comments;

import com.epam.cleancode.common.Constants;
import com.epam.cleancode.common.DateUtils;
import com.epam.cleancode.common.NumberUtils;
import com.epam.cleancode.exceptions.InvalidInputException;

public class MortgageInstallmentCalculator {
    public static double calculateMonthlyPayment(int principalAmount,
                                                 int termOfMortgageInYears,
                                                 double rateOfInterests) throws InvalidInputException {
        MortgageParameters parameters = new MortgageParameters(principalAmount, termOfMortgageInYears, rateOfInterests);

        if (parameters.isInvalid()) {
            throw new InvalidInputException(Constants.NEGATIVE_VALUE_EXCEPTION_MSG);
        }

        double termOfMortgageInMonths = DateUtils.inMonths(parameters.getTermOfMortgageInYears());

        if (rateOfInterestsAbsent(parameters))
            return parameters.getPrincipalAmount() / termOfMortgageInMonths;

        double monthlyRate = monthlyRate(parameters);

        double dividend = parameters.getPrincipalAmount() * monthlyRate;
        double divisor = 1 - Math.pow(1 + monthlyRate, -termOfMortgageInMonths);

        return dividend / divisor;
    }

    private static double monthlyRate(MortgageParameters parameters) {
        return NumberUtils.shiftDecimalPoint(parameters.getRateOfInterests(), Constants.TWO_SIGNS) / Constants.MONTHS_OF_YEAR;
    }

    private static boolean rateOfInterestsAbsent(MortgageParameters parameters) {
        return NumberUtils.isZero(parameters.getRateOfInterests());
    }
}

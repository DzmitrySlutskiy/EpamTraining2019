package com.epam.cleancode.comments;

import com.epam.cleancode.common.NumberUtils;

class MortgageParameters {
    private int principalAmount;
    private int termOfMortgageInYears;
    private double rateOfInterests;

    MortgageParameters(int principalAmount, int termOfMortgageInYears, double rateOfInterests) {
        this.principalAmount = principalAmount;
        this.termOfMortgageInYears = termOfMortgageInYears;
        this.rateOfInterests = rateOfInterests;
    }

    boolean isInvalid() {
        return NumberUtils.isNegative(this.principalAmount) ||
                NumberUtils.isNegativeOrZero(this.termOfMortgageInYears) ||
                NumberUtils.isNegative(this.rateOfInterests);
    }

    int getPrincipalAmount() {
        return principalAmount;
    }

    int getTermOfMortgageInYears() {
        return termOfMortgageInYears;
    }

    double getRateOfInterests() {
        return rateOfInterests;
    }
}

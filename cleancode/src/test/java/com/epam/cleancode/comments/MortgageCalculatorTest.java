package com.epam.cleancode.comments;

import org.junit.Assert;
import org.junit.Test;

public class MortgageCalculatorTest {
    @Test
    public void shouldCalculateMonthlyPaymentWhenAmountsAreSmall() throws Exception {
        double monthlyPaymentAmount = MortgageCalculator.calculateMonthlyPayment(1000, 1, 12);
        Assert.assertEquals(88.84d, monthlyPaymentAmount, 0.01d);
    }

    @Test
    public void shouldCalculateMonthlyPaymentWhenAmountIsLarge() throws Exception {
        double monthlyPaymentAmount = MortgageCalculator.calculateMonthlyPayment(10000000, 1, 12);
        Assert.assertEquals(888487.88d, monthlyPaymentAmount, 0.01d);
    }

    @Test
    public void shouldCalculateMonthlyPaymentWhenPrincipalIsZero() throws Exception {
        double monthlyPaymentAmount = MortgageCalculator.calculateMonthlyPayment(0, 1, 12);
        Assert.assertEquals(0, monthlyPaymentAmount, 0.01d);
    }

    @Test
    public void shouldCalculateMonthlyPaymentWhenInterestRateIsZero() throws Exception {
        double monthlyPaymentAmount = MortgageCalculator.calculateMonthlyPayment(1000, 1, 0);
        Assert.assertEquals(83.33, monthlyPaymentAmount, 0.01d);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionOnNegativeTenure() throws Exception {
        MortgageCalculator.calculateMonthlyPayment(20, -10, 14.5);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionOnNegativeInterestRate() throws Exception {
        MortgageCalculator.calculateMonthlyPayment(20, 1, -12);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionOnNegativePrincipalAmount() throws Exception {
        MortgageCalculator.calculateMonthlyPayment(-20, 10, 14.5);
    }
}

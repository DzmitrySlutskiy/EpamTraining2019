package com.epam.cleancode.singleresponsibility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CalculatorTest {

    private Calculator calc = new Calculator();

    @Test
    public void shouldAddAllNumbers() {
        add("", "summa: 0");
        add("1", "summa: 1");
        add("1,1", "summa: 2");
        add("48,222", "summa: 270");
        add("1,2,3", "summa: 6");
        add(",1,2,,,,3,", "summa: 6");
    }

    @Test(expected = WrongFormatException.class)
    public void shouldThrowWrongFormatExceptionWhenInputForAddIsNull() {
        calc.add(null);
    }

    @Test(expected = WrongFormatException.class)
    public void shouldThrowWrongFormatExceptionWhenInputForAddIsNotDigits() {
        calc.add("abc");
    }

    @Test(expected = WrongFormatException.class)
    public void shouldThrowWrongFormatExceptionWhenInputForAddWithWrongSeparator() {
        calc.add("1|2|3");
    }

    @Test
    public void shouldMultiplyAllNumbers() {
        multiply("", "product: 1");
        multiply("0", "product: 0");
        multiply("2", "product: 2");
        multiply("788996,0", "product: 0");
        multiply("33,20", "product: 660");
        multiply(",1,2,,,,3,", "product: 6");
    }

    @Test(expected = WrongFormatException.class)
    public void shouldThrowWrongFormatExceptionWhenInputForMultiplyIsNull() {
        calc.multiply(null);
    }

    @Test(expected = WrongFormatException.class)
    public void shouldThrowWrongFormatExceptionWhenInputForMultiplyIsNotDigits() {
        calc.multiply("xyz");
    }

    @Test(expected = WrongFormatException.class)
    public void shouldThrowWrongFormatExceptionWhenInputForMultiplyWithWrongSeparator() {
        calc.multiply("2-5-3");
    }

    private void add(String numbers, String expected) {
        assertEquals(expected, calc.add(numbers));
    }

    private void multiply(String numbers, String expected) {
        assertEquals(expected, calc.multiply(numbers));
    }
}

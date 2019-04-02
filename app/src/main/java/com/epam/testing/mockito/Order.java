package com.epam.testing.mockito;

public class Order {

    private final String firstDish;

    private final String secondDish;

    public Order(final String pFirstDish, final String pSecondDish) {
        firstDish = pFirstDish;
        secondDish = pSecondDish;
    }

    public String getFirstDish() {
        System.out.println("getFirstDish() called");

        return firstDish;
    }

    public String getSecondDish() {
        System.out.println("getSecondDish() called");

        return secondDish;
    }
}

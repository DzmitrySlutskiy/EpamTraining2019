package com.epam.testing.mockito;

public class Meal {

    private final String firstDish;

    private final String secondDish;

    public Meal(final String pFirstDish, final String pSecondDish) {
        firstDish = pFirstDish;
        secondDish = pSecondDish;
    }

    public String getFirstDish() {
        return firstDish;
    }

    public String getSecondDish() {
        return secondDish;
    }
}

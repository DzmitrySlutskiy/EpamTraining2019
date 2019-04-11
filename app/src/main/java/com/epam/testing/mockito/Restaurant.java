package com.epam.testing.mockito;

public class Restaurant {

    private final Chef mChef;

    public Restaurant(final Chef pChef) {
        this.mChef = pChef;
    }

    public Meal orderMeal(final Order pOrder) {
        return new Meal(mChef.cookFirstDish(pOrder), mChef.cookSecondDish(pOrder));
    }
}

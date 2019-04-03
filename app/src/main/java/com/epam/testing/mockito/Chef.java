package com.epam.testing.mockito;

public class Chef {

    public String cookFirstDish(final Order pOrder) {
        System.out.println("cookFirstDish() called");

        return pOrder.getFirstDish() + " cooked";
    }

    public String cookSecondDish(final Order pOrder) {
        System.out.println("cookSecondDish() called");

        return pOrder.getSecondDish() + " cooked";
    }
}

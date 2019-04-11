package com.epam.testing.mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestRestaurant {

    @Mock
    Chef chef;

    @Mock
    Order order;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOrderMeal_mockChef() {
        when(chef.cookFirstDish(any(Order.class))).thenReturn("FirstDish");
        doReturn("SecondDish").when(chef).cookSecondDish(any(Order.class));

        final Restaurant restaurant = new Restaurant(chef);
        final Order order = new Order("soup", "beef");
        final Meal meal = restaurant.orderMeal(order);

        Assert.assertEquals("FirstDish", meal.getFirstDish());
        Assert.assertEquals("SecondDish", meal.getSecondDish());

        verify(chef, Mockito.times(1)).cookFirstDish(any(Order.class));
    }

    @Test
    public void testOrderMeal_spyChef() {
        final Chef chef = spy(new Chef());
        final Restaurant restaurant = new Restaurant(chef);
        final Order order = new Order("soup", "beef");
        final Meal meal = restaurant.orderMeal(order);

        Assert.assertEquals("soup cooked", meal.getFirstDish());
        Assert.assertEquals("beef cooked", meal.getSecondDish());

        verify(chef, Mockito.times(1)).cookFirstDish(any(Order.class));
        verify(chef, Mockito.times(1)).cookSecondDish(any(Order.class));
    }

    @Test
    public void testVerifyFunction_mockOrder() {
        when(order.getFirstDish()).thenReturn("soup");
        doReturn("potato").when(order).getSecondDish();

        final Chef chef = new Chef();
        final Restaurant restaurant = new Restaurant(chef);
        final Meal meal = restaurant.orderMeal(order);

        Assert.assertEquals("soup cooked", meal.getFirstDish());
        Assert.assertEquals("potato cooked", meal.getSecondDish());

        verify(order, Mockito.times(1)).getFirstDish();
    }
}

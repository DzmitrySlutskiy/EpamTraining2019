package com.epam.cleancode.impl;

import com.epam.cleancode.codequalitymetrics.exception.InvalidDataException;
import com.epam.cleancode.codequalitymetrics.service.RestaurantSatisfactionService;
import com.epam.cleancode.codequalitymetrics.service.impl.RestaurantSatisfactionServiceImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class RestaurantSatisfactionServiceImplTest {
    RestaurantSatisfactionService restaurantSatisfaction;

    @Before
    public void init() {
        restaurantSatisfaction = new RestaurantSatisfactionServiceImpl();
    }

    @Test()
    public void should_returnMaximumSatisfaction() {
        try {
            int maxValue = restaurantSatisfaction.getMaxSatisfaction("input.txt");
            Assert.assertEquals(22, maxValue);
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    @Test(expected = InvalidDataException.class)
    public void should_throwException_when_lessItemsArePassedThanSpecified() {
        try {
            restaurantSatisfaction.getMaxSatisfaction("input-with-less-items-than-mentioned.txt");
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    @Test(expected = InvalidDataException.class)
    public void should_throwException_when_moreItemsArePassedThanSpecified() {
        try {
            restaurantSatisfaction.getMaxSatisfaction("input-with-more-items-than-mentioned.txt");
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }


    @Test()
    public void should_returnZero_when_totalTimeAvailableIsZero() {
        try {
            int maxValue = restaurantSatisfaction.getMaxSatisfaction("input-with-totaltime-zero-minutes.txt");
            Assert.assertEquals(0, maxValue);
        } catch (IOException | URISyntaxException | InvalidDataException exception) {
            exception.printStackTrace();
        }
    }

    @Test()
    public void should_returnMaxSatisfaction_when_totalTimeIsGreaterThanSumOfAllItems() {
        try {
            int maxValue = restaurantSatisfaction.getMaxSatisfaction("input-with-totaltime-greaterthan-sum-of-all-items.txt");
            Assert.assertEquals(23, maxValue);
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    @Test(expected = InvalidDataException.class)
    public void should_throwException_when_totalTimeIsInvalid() {
        try {
            restaurantSatisfaction.getMaxSatisfaction("input-with-totaltime-as-negative-numbers.txt");
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    @Test(expected = InvalidDataException.class)
    public void should_throwException_when_numberOfItemsIsInvalid() {
        try {
            restaurantSatisfaction.getMaxSatisfaction("input-with-items-as-negative-numbers.txt");
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    @Test(expected = InvalidDataException.class)
    public void should_throwException_when_itemSatisfactionValueIsInvalid() {
        try {
            restaurantSatisfaction.getMaxSatisfaction("input-with-item-eating-satisfaction-as-negative-numbers.txt");
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    @Test(expected = InvalidDataException.class)
    public void should_throwException_when_itemItemValueIsInvalid() {
        try {
            restaurantSatisfaction.getMaxSatisfaction("input-with-item-eating-time-as-negative-numbers.txt");
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    @Test(expected = InvalidDataException.class)
    public void should_throwException_when_valuesArePassedAsString() {
        try {
            restaurantSatisfaction.getMaxSatisfaction("input-with-invalid-data.txt");
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

}

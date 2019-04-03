package com.epam.testing.tdd;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AgeUtilTest {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass() called");
    }

    @Before
    public void setUp() {
        System.out.println("setUp() called");
    }

    @After
    public void onTestEnd() {
        System.out.println("onTestEnd() called");
    }

    @AfterClass
    public static void onTestClassEnd() {
        System.out.println("onTestClassEnd() called");
    }

    @Test
    public void testKid() {
        System.out.println("testKid() called");
        Assert.assertEquals(AgeStatus.KID, AgeUtil.getAgeStatus(5));
    }

    @Test
    public void testTeenager() {
        System.out.println("testTeenager() called");
        Assert.assertEquals(AgeStatus.TEENAGER, AgeUtil.getAgeStatus(13));
    }

    @Test
    public void testAdult() {
        System.out.println("testAdult() called");
        Assert.assertEquals(AgeStatus.ADULT, AgeUtil.getAgeStatus(34));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOutOfRange() {
        System.out.println("testOutOfRange() called");
        AgeUtil.getAgeStatus(-1);
    }
}

package com.epam.testing;

import com.epam.testing.mockito.TestRestaurant;
import com.epam.testing.tdd.AgeUtilTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestRestaurant.class,
        AgeUtilTest.class
})
public class TestSuite {

}

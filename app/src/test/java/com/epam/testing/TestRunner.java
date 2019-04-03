package com.epam.testing;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public final class TestRunner {

    public static void main(final String[] args) {
        final Result result = JUnitCore.runClasses(TestSuite.class);

        System.out.println(result.wasSuccessful());
    }
}

package com.epam.cleancode.common;

import java.math.BigDecimal;

public abstract class NumberUtils {
    public static boolean isNegativeOrZero(double value) {
        return isNegative(value) || isZero(value);
    }

    public static boolean isZero(double value) {
        return value == 0;
    }

    public static boolean isNegative(double value) {
        return value < 0;
    }

    public static Double shiftDecimalPoint(double value, int step) {
        return new BigDecimal(value).scaleByPowerOfTen(-step).doubleValue();
    }
}

package com.epam.testing.tdd;

public class AgeUtil {

    public static AgeStatus getAgeStatus(final int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid age argument:" + age);
        } else if (age < 13) {
            return AgeStatus.KID;
        } else if (age < 20) {
            return AgeStatus.TEENAGER;
        } else {
            return AgeStatus.ADULT;
        }
    }
}

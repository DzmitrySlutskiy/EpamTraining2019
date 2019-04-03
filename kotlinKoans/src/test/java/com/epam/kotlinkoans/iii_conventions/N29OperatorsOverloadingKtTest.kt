package iii_conventions

import iii_conventions.TimeInterval.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class N29OperatorsOverloadingKtTest {
    /* Month numbering starts with 0 (0-Jan, 1-Feb, ... 11-Dec) */
    @Test
    fun testAddTimeIntervals() {
        assertEquals(MyDate(2014, 5, 22), MyDate(1983, 5, 22).addTimeIntervals(YEAR, 31))
        assertEquals(MyDate(1983, 5, 29), MyDate(1983, 5, 22).addTimeIntervals(DAY, 7))
        assertEquals(MyDate(1983, 5, 29), MyDate(1983, 5, 22).addTimeIntervals(WEEK, 1))
    }

    @Test
    fun testAddOneTimeInterval() {
        assertEquals(MyDate(2015, 5, 8), task29_1(MyDate(2014, 5, 1)))
    }

    @Test
    fun testOneMonth() {
        assertEquals(MyDate(2016, 0, 27), task29_2(MyDate(2014, 0, 1)))
    }

    @Test
    fun testMonthChange() {
        assertEquals(MyDate(2016, 1, 20), task29_2(MyDate(2014, 0, 25)))
    }
}
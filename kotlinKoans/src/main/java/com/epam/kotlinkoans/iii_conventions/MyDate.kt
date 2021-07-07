package iii_conventions

import java.time.LocalDate
import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) =
        when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }


operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(interval: TimeInterval): MyDate = addTimeIntervals(interval, 1)

operator fun MyDate.plus(interval: RepeatedTimeInterval): MyDate = addTimeIntervals(interval.ti, interval.n)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(n: Int) = RepeatedTimeInterval(this, n)

data class RepeatedTimeInterval(val ti: TimeInterval, val n: Int) {}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {

    operator fun contains(d: MyDate) = d >= start && d <= endInclusive

    override fun iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
        var currentDate: MyDate = start

        override fun hasNext() = currentDate <= endInclusive

        override fun next() : MyDate  {
            val result = currentDate
            currentDate = currentDate.nextDay()

            return result
        }
    }
}


package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    // task 25
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

class DateRange(val start: MyDate, val endInclusive: MyDate)

// task 26
operator fun DateRange.contains(date: MyDate) = date >= start && date <= endInclusive

// task 27
operator fun MyDate.rangeTo(other: MyDate) = DateRange(this, other)

// task 28
operator fun DateRange.iterator() = object : Iterator<MyDate> {
    var current = start

    override fun hasNext(): Boolean {
        return current <= endInclusive
    }

    override fun next(): MyDate {
        return current.apply { current = current.nextDay() }
    }
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

// task 29
data class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

operator fun MyDate.plus(interval: TimeInterval): MyDate = addTimeIntervals(interval, 1)

operator fun MyDate.plus(interval: RepeatedTimeInterval) : MyDate = addTimeIntervals(interval.ti, interval.n)

operator fun TimeInterval.times(n: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, n)
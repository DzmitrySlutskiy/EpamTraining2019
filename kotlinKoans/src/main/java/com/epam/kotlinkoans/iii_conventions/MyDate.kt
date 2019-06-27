package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (year > other.year) return 1
        if (year < other.year) return -1
        if (month > other.month) return 1
        if (month < other.month) return -1
        if (dayOfMonth > other.dayOfMonth) return 1
        if (dayOfMonth < other.dayOfMonth) return -1
        return 0
    }
}

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = addTimeIntervals(timeInterval, 1)

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval) = addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.number)

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(number: Int) = RepeatedTimeInterval(this, number)
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {
    var date: MyDate = start

    override fun hasNext() = date <= endInclusive

    override fun next(): MyDate {
        val resultDate = date
        date = date.nextDay()
        return resultDate
    }

    operator fun contains(d: MyDate) = d >= start && d <= endInclusive
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)
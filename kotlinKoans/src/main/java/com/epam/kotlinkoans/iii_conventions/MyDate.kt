package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
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

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(other: TimeInterval): MyDate = addTimeIntervals(other, 1)

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)

operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)

operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval) = addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate):Iterable<MyDate>{
    override fun iterator(): Iterator<MyDate> = DateRangeIterator(this)

    operator fun contains(d: MyDate):Boolean{
        return d >= start && d <= endInclusive
    }
}

class DateRangeIterator(val date: DateRange):Iterator<MyDate>{
    var current: MyDate = date.start
    override fun hasNext(): Boolean {
        return current <= date.endInclusive
    }

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }

}

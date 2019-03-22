package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)

operator fun MyDate.compareTo(other: MyDate): Int = compareValuesBy(this, other, MyDate::year, MyDate::month, MyDate::dayOfMonth)

operator fun DateRange.contains(date: MyDate) = date >= start && date <= endInclusive

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun DateRange.iterator(): Iterator<MyDate> =
        object : Iterator<MyDate> {
            var current = start

            override fun hasNext(): Boolean {
                return current <= endInclusive
            }

            override fun next(): MyDate {
                return current.apply {
                    current = current.nextDay()
                }
            }
        }

operator fun MyDate.plus(interval: TimeInterval) : MyDate = addTimeIntervals(interval, 1)

data class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

operator fun TimeInterval.times(n: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, n)

operator fun MyDate.plus(interval: RepeatedTimeInterval) : MyDate = addTimeIntervals(interval.ti, interval.n)

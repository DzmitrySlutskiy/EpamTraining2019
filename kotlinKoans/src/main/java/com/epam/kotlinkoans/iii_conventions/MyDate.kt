package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)
operator fun MyDate.plus(interval: TimeInterval): MyDate = addTimeIntervals(interval, 1)

data class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

operator fun TimeInterval.times(n: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, n)

operator fun MyDate.plus(interval: RepeatedTimeInterval): MyDate = addTimeIntervals(interval.ti, interval.n)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var current: MyDate = start

            override fun next(): MyDate {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }

                val result = current
                current = current.nextDay()
                return result
            }

            override fun hasNext(): Boolean {
                return current <= endInclusive
            }


        }
    }

    operator fun contains(date: MyDate): Boolean {
        return start <= date && date <= endInclusive
    }

}

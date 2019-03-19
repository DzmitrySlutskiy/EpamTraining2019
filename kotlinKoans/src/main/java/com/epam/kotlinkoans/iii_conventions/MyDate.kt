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

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate){
    operator fun contains(d: MyDate) = d >= start && d <= endInclusive
}

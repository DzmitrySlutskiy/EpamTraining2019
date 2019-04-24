package iii_conventions.multiAssignemnt

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

fun isLeapDay(date: MyDate): Boolean {
    val (year, month, dayOfMonth) = date

    return isLeapYear(year) && month == 1 && dayOfMonth == 29
}

fun isLeapYear(year: Int): Boolean = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)

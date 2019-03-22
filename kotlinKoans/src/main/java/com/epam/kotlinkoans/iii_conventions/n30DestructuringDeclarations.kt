package iii_conventions.multiAssignemnt

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

fun isLeapDay(date: MyDate): Boolean {
    val (year, month, dayOfMonth) = date

    return year % 4 == 0 && month == 2 && dayOfMonth == 29
}
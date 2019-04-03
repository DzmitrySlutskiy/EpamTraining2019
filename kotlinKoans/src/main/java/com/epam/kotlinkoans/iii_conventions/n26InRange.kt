package iii_conventions

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean =
    date in DateRange(first, last)


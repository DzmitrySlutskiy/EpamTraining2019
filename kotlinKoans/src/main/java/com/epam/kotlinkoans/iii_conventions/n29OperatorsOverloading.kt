package iii_conventions

import iii_conventions.TimeInterval.*

fun task29_1(today: MyDate): MyDate {
    return today + YEAR + WEEK
}

fun task29_2(today: MyDate): MyDate {
    return today + YEAR * 2 + WEEK * 3 + DAY * 5
}


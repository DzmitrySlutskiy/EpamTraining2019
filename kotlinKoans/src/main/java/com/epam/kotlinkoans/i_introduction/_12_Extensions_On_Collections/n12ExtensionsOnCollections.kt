package i_introduction._12_Extensions_On_Collections

import java.util.*

fun task12(): List<Int> {
    val list = arrayListOf(1, 5, 2)
    return list.sortedDescending()
}

fun List<Int>.sortedDescending(): List<Int> {
    Collections.sort(this) { firstObject, secondObject ->
        when {
            secondObject < firstObject -> -1
            secondObject == firstObject -> 0
            else -> 1
        }
    }

    return this
}
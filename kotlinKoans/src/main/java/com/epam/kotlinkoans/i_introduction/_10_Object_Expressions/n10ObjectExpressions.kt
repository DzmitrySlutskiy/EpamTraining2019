package i_introduction._10_Object_Expressions

import java.util.*

fun task10(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList, object : Comparator<Int> {
        override fun compare(firstObject: Int, secondObject: Int): Int {
            if (secondObject < firstObject) {
                return -1
            } else if (secondObject == firstObject) {
                return 0
            } else {
                return 1
            }
        }
    })

    return arrayList
}
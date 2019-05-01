package i_introduction._10_Object_Expressions

import java.util.*

fun task10(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)

    Collections.sort(arrayList, Collections.reverseOrder())

    return arrayList
}
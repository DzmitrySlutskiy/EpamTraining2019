package i_introduction._10_Object_Expressions

import java.util.*
import kotlin.Comparator

fun task10(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)

    Collections.sort(arrayList, object : Comparator<Int> {
        override fun compare(x: Int, y: Int): Int {
            return when {
                x > y -> -1
                x == y -> 0
                else -> 1
            }
        }
    })

    return arrayList
}
package i_introduction._11_SAM_Conversions

import java.util.*

fun task11(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)

    Collections.sort(arrayList, { x, y ->
        when {
            x > y -> -1
            x == y -> 0
            else -> 1
        }
    })

    return arrayList
}

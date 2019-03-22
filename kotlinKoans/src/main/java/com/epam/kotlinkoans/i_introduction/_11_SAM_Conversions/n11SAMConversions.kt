package i_introduction._11_SAM_Conversions

import java.util.*

fun task11(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    arrayList.sortWith(Comparator { fristObject, secondObject ->
        if (secondObject < fristObject) {
            -1
        } else if (secondObject == fristObject) {
            0
        } else {
            1
        }
    })

    return arrayList
}
package i_introduction._11_SAM_Conversions

fun task11(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    arrayList.sortWith(Comparator { x, y ->  y.compareTo(x)})
    return arrayList
}

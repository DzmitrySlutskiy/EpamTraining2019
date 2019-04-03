package i_introduction._12_Extensions_On_Collections


fun task12(): List<Int> {
    return arrayListOf(1, 5, 2).sortedDescending()
}

fun List<Int>.sortedDescending():List<Int>{
    return sortedWith(Comparator { x, y -> y.compareTo(x) })
}


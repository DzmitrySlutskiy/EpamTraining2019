package i_introduction._12_Extensions_On_Collections

fun task12(): List<Int> {
    val list = arrayListOf(1, 5, 2)
    return list.sorted().reversed()
}

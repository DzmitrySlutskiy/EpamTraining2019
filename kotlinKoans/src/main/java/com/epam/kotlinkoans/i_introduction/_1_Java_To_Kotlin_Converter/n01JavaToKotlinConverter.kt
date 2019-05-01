package i_introduction._1_Java_To_Kotlin_Converter

fun task1(collection: Collection<Int>): String {
    val stringBuilder = StringBuilder()
    val iterator = collection.iterator()
    var element: Int

    stringBuilder.append("{")

    while (iterator.hasNext()) {
        element = iterator.next()
        stringBuilder.append(element)

        if (iterator.hasNext()) {
            stringBuilder.append(", ")
        }
    }
    stringBuilder.append("}")

    return stringBuilder.toString()
}

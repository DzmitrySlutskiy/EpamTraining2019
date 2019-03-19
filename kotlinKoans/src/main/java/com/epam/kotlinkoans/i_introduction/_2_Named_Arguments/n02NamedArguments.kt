package i_introduction._2_Named_Arguments

fun task2(collection: Collection<Int>): String {
    return collection.joinToString(prefix = "{", separator = ", ", postfix = "}")
}
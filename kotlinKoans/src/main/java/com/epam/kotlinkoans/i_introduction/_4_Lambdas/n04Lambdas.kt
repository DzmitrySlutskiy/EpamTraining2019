package i_introduction._4_Lambdas

fun task4(collection: Collection<Int>): Boolean {
    return collection.filter { it % 2 == 0 }.any()
}
package i_introduction._4_Lambdas

fun task4(collection: Collection<Int>): Boolean = collection.any { element: Int -> element % 2 == 0 }
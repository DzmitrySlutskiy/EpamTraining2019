package i_introduction._4_Lambdas

import com.epam.kotlinkoans.i_introduction._4_Lambdas.JavaCode4
import util.TODO
import util.doc4

fun example() {

    val sum = { x: Int, y: Int -> x + y }
    val square: (Int) -> Int = { x -> x * x }

    sum(1, square(2)) == 5
}

fun task4(collection: Collection<Int>): Boolean = collection.any { it % 2 == 0 }

package i_introduction._1_Java_To_Kotlin_Converter

import com.epam.kotlinkoans.i_introduction._1_Java_To_Kotlin_Converter.JavaCode1
import util.TODO

fun todoTask1(collection: Collection<Int>): Nothing = TODO(
    """
        Task 1.
        Convert the Java method 'task1' of the class 'JavaCode1' into Kotlin.
        In IntelliJ IDEA or Android Studio, you can copy the Java code,
        paste it into the Kotlin file and let IDE convert it.
        Please use automatic conversion for this task only.
    """,
    references = { JavaCode1().task1(collection) })


fun task1(collection: Collection<Int>): String {
    todoTask1(collection)
}
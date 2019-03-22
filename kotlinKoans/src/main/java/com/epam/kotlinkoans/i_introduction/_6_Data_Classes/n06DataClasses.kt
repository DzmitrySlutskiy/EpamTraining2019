package i_introduction._6_Data_Classes


class JavaCode6() {
    data class Person(val name: String, val age: Int)
}

fun task6(): List<JavaCode6.Person> {
    return listOf(JavaCode6.Person("Alice", 29), JavaCode6.Person("Bob", 31))
}


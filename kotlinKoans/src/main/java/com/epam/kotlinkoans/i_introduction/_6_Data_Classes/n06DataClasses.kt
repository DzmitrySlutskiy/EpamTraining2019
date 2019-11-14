package i_introduction._6_Data_Classes

data class Person(val name: String, val age: Int)

fun task6(): List<Person> =
    listOf(Person("Alice", 29), Person("Bob", 31))



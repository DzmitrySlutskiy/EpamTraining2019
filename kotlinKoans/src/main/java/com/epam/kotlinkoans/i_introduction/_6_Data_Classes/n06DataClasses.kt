package i_introduction._6_Data_Classes

import com.epam.kotlinkoans.i_introduction._6_Data_Classes.JavaCode6
import com.epam.kotlinkoans.i_introduction._6_Data_Classes.Person
import util.TODO
import util.doc6

fun todoTask6(): Nothing = TODO(
    """
        Convert 'JavaCode6.Person' class to Kotlin.
        Then add a modifier `data` to the resulting class.
        This annotation means the compiler will generate a bunch of useful methods in this class:
        `equals`/`hashCode`, `toString` and some others.
        The `task6` function should return a list of persons.
    """,
    documentation = doc6(),
    references = { JavaCode6.Person("Alice", 29) }
)


fun task6(): List<Person> = listOf(Person("Alice", 29), Person("Bob", 31))



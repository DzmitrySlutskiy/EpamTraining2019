package iii_conventions

import util.TODO

class Invokable {
    private var numberOfInvocations = 0
    operator fun invoke(): Invokable {
        numberOfInvocations++
        return this
    }

    fun getNumberOfInvocations(): Int {
        return numberOfInvocations
    }
}

fun todoTask31(): Nothing = TODO(
        """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
        references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    //todoTask31()
    return invokable()()()().getNumberOfInvocations()
}




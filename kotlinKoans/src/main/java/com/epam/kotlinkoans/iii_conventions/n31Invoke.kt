package iii_conventions

import util.TODO


class Invokable {
    var invokeCount: Int = 0
    operator fun invoke(): Invokable {
        invokeCount++
        return this
    }

    fun getNumberOfInvocations() = invokeCount
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}

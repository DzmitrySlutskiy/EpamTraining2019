package iii_conventions

class Invokable {
    private var numberOfInvocations: Int = 0

    operator fun invoke(): Invokable {
        numberOfInvocations++

        return this
    }

    fun getNumberOfInvocations(): Int {
        return numberOfInvocations
    }
}

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}
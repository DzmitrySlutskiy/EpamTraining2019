package iii_conventions

class Invokable {
    private var invokeCount = 0
    operator fun invoke(): Invokable {
        invokeCount++
        return this
    }

    fun getNumberOfInvocations(): Int {
        return invokeCount
    }
}

fun task31(invokable: Invokable): Int = invokable()()()().getNumberOfInvocations()


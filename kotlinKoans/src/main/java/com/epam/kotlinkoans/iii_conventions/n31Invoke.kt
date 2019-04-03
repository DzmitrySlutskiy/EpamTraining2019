package iii_conventions

class Invokable {
    var numberOfInvocations: Int = 0
        private set

    operator fun invoke(): Invokable {
        numberOfInvocations++

        return this
    }
}

fun task31(invokable: Invokable) = invokable()()()()

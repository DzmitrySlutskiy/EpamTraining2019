package i_introduction._9_Extension_Functions

// declares an extension function that returns the last character
fun String.lastChar() = this.get(this.length - 1)


// 'this' refers to the receiver (String) and can be omitted
fun String.lastChar1() = get(length - 1)

fun useExtensionFunction() {
    // try Ctrl+Space "default completion" after the dot: lastChar() is visible
    "abc".lastChar()
}

data class RationalNumber(val numerator: Int, val denominator: Int)

fun Int.r(): RationalNumber = RationalNumber(this, 1)
fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(first, second)



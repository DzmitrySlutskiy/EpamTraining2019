package i_introduction._9_Extension_Functions

fun String.lastChar() = this.get(this.length - 1)

fun String.lastChar1() = get(length - 1)

fun useExtensionFunction() {
    "abc".lastChar()
}

data class RationalNumber(val numerator: Int, val denominator: Int)

fun Int.r(): RationalNumber = RationalNumber(this, 1)

fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(first, second)



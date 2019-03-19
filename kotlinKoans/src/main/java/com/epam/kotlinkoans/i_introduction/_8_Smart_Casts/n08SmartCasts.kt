package i_introduction._8_Smart_Casts

import com.epam.kotlinkoans.i_introduction._8_Smart_Casts.JavaCode8
import util.TODO
import util.doc8

// 'sealed' modifier restricts the type hierarchy:
// all the subclasses must be declared in the same file
sealed class Expr

class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

fun eval(e: Expr): Int =
    when (e) {
        is Num -> todoTask8(e)
        is Sum -> todoTask8(e)
    }

fun todoTask8(expr: Expr): Nothing = TODO(
    """
        Task 8.
        Complete the implementation of the 'eval' function above using smart casts and 'when' expression.
        The 'JavaCode8.eval' method provides the similar functionality written in Java.
    """,
    documentation = doc8(),
    references = { JavaCode8().eval(expr) })


package i_introduction._8_Smart_Casts

import com.epam.kotlinkoans.i_introduction._8_Smart_Casts.JavaCode8

// 'sealed' modifier restricts the type hierarchy:
// all the subclasses must be declared in the same file
sealed class Expr

class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

fun eval(e: Expr): Int =
        when (e) {
            is Num -> JavaCode8().eval(e)
            is Sum -> JavaCode8().eval(e)
        }

class JavaCode8 {
    fun eval(expr: Expr): Int {
        if (expr is Num) {
            return expr.value
        }
        if (expr is Sum) {
            return eval(expr.left) + eval(expr.right)
        }
        throw IllegalArgumentException("Unknown expression")
    }
}



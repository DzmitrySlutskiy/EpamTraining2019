package com.epam.kotlinkoans.i_introduction._10_Object_Expressions

import i_introduction._10_Object_Expressions.task10
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class N10ObjectExpressionsKtTest {
    @Test
    fun testSort() {
        assertEquals(listOf(5, 2, 1), task10())
    }
}

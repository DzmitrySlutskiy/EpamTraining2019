package com.epam.kotlinkoans.i_introduction._2_Named_Arguments

import i_introduction._2_Named_Arguments.task2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class N02NamedArgumentsKtTest {

    @Test
    fun testJoinToString() {
        assertEquals("{1, 2, 3, 42, 555}", task2(listOf(1, 2, 3, 42, 555)))
    }

}

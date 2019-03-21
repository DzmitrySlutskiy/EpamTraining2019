package com.epam.kotlinkoans.i_introduction._4_Lambdas

import i_introduction._4_Lambdas.task4
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class N04LambdasKtTest {
    @Test
    fun contains() {
        assertTrue(task4(listOf(1, 2, 3)))
    }

    @Test
    fun notContains() {
        assertFalse(task4(listOf(1, 3, 5)))
    }
}
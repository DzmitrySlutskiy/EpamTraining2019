package com.epam.kotlinkoans.i_introduction._11_SAM_Conversions

import i_introduction._11_SAM_Conversions.task11
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class N11SAMConversionsKtTest {
    @Test
    fun testSort() {
        assertEquals(listOf(5, 2, 1), task11())
    }
}

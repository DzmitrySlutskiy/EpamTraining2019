package i_introduction._1_Java_To_Kotlin_Converter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class N01JavaToKotlinConverterKtTest {
    @Test
    fun collection() {
        assertEquals("{1, 2, 3, 42, 555}", task1(listOf(1, 2, 3, 42, 555)))
    }
}

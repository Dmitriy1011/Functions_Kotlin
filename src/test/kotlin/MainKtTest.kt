import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateCommission() {
        val maestro = "MAESTRO"
        val mastercard = "MASTERCARD"
        val visa = "VISA"
        val mir = "MIR"
        val vkPay = "VKPAY"

        val result = calculateCommission(maestro, 700_000)

        assertEquals(-1, result)
    }
}
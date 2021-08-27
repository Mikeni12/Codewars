import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.test.assertEquals

/*
The rgb function is incomplete. Complete it so that passing in RGB decimal values will result in a hexadecimal
representation being returned. Valid decimal values for RGB are 0 - 255. Any values that fall out of that range must be
rounded to the closest valid value.

Note: Your answer should always be 6 characters long, the shorthand with 3 will not work here.

The following are examples of expected output values:

rgb(255, 255, 255) // returns FFFFFF
rgb(255, 255, 300) // returns FFFFFF
rgb(0, 0, 0) // returns 000000
rgb(148, 0, 211) // returns 9400D3
 */

fun rgb(r: Int, g: Int, b: Int) = listOf(r, g, b).joinToString("") { it.toHexString() }

private fun Int.toHexString() = "%02X".format(0xFF and closesValue())

private fun Int.closesValue() = (0..255).toList().minBy { abs(it - this) } ?: 0

class Example {
    @Test
    fun testFixed() {
        assertEquals("000000", rgb(0, 0, 0))
        assertEquals("000000", rgb(0, 0, -20))
        assertEquals("FFFFFF", rgb(300, 255, 255))
        assertEquals("ADFF2F", rgb(173, 255, 47))
        assertEquals("9400D3", rgb(148, 0, 211))
    }
}

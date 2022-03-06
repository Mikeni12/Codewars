import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
Clock shows h hours, m minutes and s seconds after midnight.

Your task is to write a function which returns the time since midnight in milliseconds.

Example:
h = 0
m = 1
s = 1

result = 61000
Input constraints:

0 <= h <= 23
0 <= m <= 59
0 <= s <= 59
*/
const val ms = 1000

fun past(h: Int, m: Int, s: Int): Int =  h * 60 * 60 * ms + m * 60 * ms + s * ms

class TestKata {
    @Test
    fun basicTests() {
        assertEquals(61000, past(0, 1, 1))
        assertEquals(3661000, past(1, 1, 1))
        assertEquals(0, past(0, 0, 0))
        assertEquals(3601000, past(1, 0, 1))
        assertEquals(3600000, past(1, 0, 0))
    }
}

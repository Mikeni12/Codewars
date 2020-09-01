import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
Write a function, persistence, that takes in a positive parameter num and returns its multiplicative persistence, which is the number of times you must multiply the digits in num until you reach a single digit.

For example:

 persistence(39) == 3 // because 3*9 = 27, 2*7 = 14, 1*4=4
                       // and 4 has only one digit

 persistence(999) == 4 // because 9*9*9 = 729, 7*2*9 = 126,
                        // 1*2*6 = 12, and finally 1*2 = 2

 persistence(4) == 0 // because 4 is already a one-digit number
 */

fun persistence(num: Int): Int = num.toString().iterate(0)

fun String.iterate(num: Int): Int {
    return if (this.length < 2) num else {
        val array = this.map { it.toString().toInt() }.toIntArray().reduce { acc, element -> acc * element }
        num + 1 + array.toString().iterate(num)
    }
}


class TestPersistence {
    @Test
    fun `Basic Tests`() {
        assertEquals(3, persistence(39))
        assertEquals(0, persistence(4))
        assertEquals(2, persistence(25))
        assertEquals(4, persistence(999))
    }
}
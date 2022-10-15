import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
Coding decimal numbers with factorials is a way of writing out numbers in a base system that depends on factorials,
rather than powers of numbers.

In this system, the last digit is always 0 and is in base 0!. The digit before that is either 0 or 1 and is in base 1!.
The digit before that is either 0, 1, or 2 and is in base 2!, etc. More generally, the nth-to-last digit is
always 0, 1, 2, ..., n and is in base n!.

Read more about it at: http://en.wikipedia.org/wiki/Factorial_number_system

Example
The decimal number 463 is encoded as "341010", because:

463 = 3×5! + 4×4! + 1×3! + 0×2! + 1×1! + 0×0!

If we are limited to digits 0..9, the biggest number we can encode is 10!-1 (= 3628799). So we extend 0..9 with
letters A..Z. With these 36 digits we can now encode numbers up to 36!-1 (= 3.72 × 1041)

Task
We will need two functions. The first one will receive a decimal number and return a string with the factorial
representation.

The second one will receive a string with a factorial representation and produce the decimal representation.

Given numbers will always be positive.
 */

object Dec2Fact {

    private val characters = listOf(('0'..'9'), ('A'..'Z')).flatten()

    private fun factorial(num: Int): Long = if (num <= 1) num.toLong() else num * factorial(num - 1)

    fun dec2FactString(n: Long) = buildString {
        var index = 1
        var total = n
        while (total != 0L) {
            append(characters[(total % index).toInt()])
            total /= index++
        }
    }.reversed()

    fun factString2Dec(str: String) = str.map { char -> characters.indexOf(char).toLong() }
        .reversed()
        .reduceIndexed { index, acc, num ->
            acc + (num * factorial(index))
        }
}

class Dec2FactTest {

    private fun testing1(nb: Long, expect: String) {
        val actual: String = Dec2Fact.dec2FactString(nb)
        assertEquals(expect, actual)
    }

    private fun testing2(str: String, expect: Long) {
        val actual: Long = Dec2Fact.factString2Dec(str)
        assertEquals(expect, actual)
    }

    @Test
    fun basicTests1() {
        testing1(36288000L, "A0000000000")
        testing1(2982L, "4041000")
        testing1(463L, "341010")
    }

    @Test
    fun basicTests2() {
        testing2("341010", 463L)
        testing2("4042100", 2990L)
        testing2("27A0533231100", 1273928000L)
    }
}

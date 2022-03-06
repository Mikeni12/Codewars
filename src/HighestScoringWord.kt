import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
Given a string of words, you need to find the highest scoring word.

Each letter of a word scores points according to its position in the alphabet: a = 1, b = 2, c = 3 etc.

You need to return the highest scoring word as a string.

If two words score the same, return the word that appears earliest in the original string.

All letters will be lowercase and all inputs will be valid.
 */

val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray()

fun high(str: String): String = str.split(" ").map { word ->
    word to word.toCharArray().sumOf { character -> alphabet.indexOf(character) + 1 }
}.maxByOrNull { it.second }?.first ?: String()

class HighestScoringWordTest {
    @Test
    fun testFixed() {
        assertEquals("taxi", high("man i need a taxi up to ubud"))
        assertEquals("volcano", high("what time are we climbing up the volcano"))
        assertEquals("semynak", high("take me to semynak"))
        assertEquals("fzdeoejdwvrjk", high("fzdeoejdwvrjk me to dknvwafwltjm"))

    }
}


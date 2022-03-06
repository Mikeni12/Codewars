import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
Ask a small girl - "How old are you?". She always says strange things... Lets help her!

For correct answer program should return int from 0 to 9.

Assume test input string always valid and may look like "1 year old" or "5 years old", etc..
The first char is number only.
 */
class ParseIntCharProblem {

    fun getAge(yearsOld: String): Int = yearsOld.takeWhile { it.isDigit() }.toInt()
}

class TestAge {

    @Test
    fun basicTests() {
        assertEquals(4, ParseIntCharProblem().getAge("4 years old"));
        assertEquals(5, ParseIntCharProblem().getAge("5 years old"));
        assertEquals(7, ParseIntCharProblem().getAge("7 years old"));
    }
}

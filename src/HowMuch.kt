import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

fun howmuch(m: Int, n: Int): String =
    (Math.min(m, n)..Math.max(m, n))
        .filter { money -> (money - 2).rem(7) == 0 && (money - 1).rem(9) == 0 }
        .joinToString("", "[", "]") { "[M: $it B: ${it / 7} C: ${it / 9}]" }

class CarboatTest {
    @Test
    fun BasicTests() {
        println("****** Basic tests ******")
        assertEquals("[[M: 37 B: 5 C: 4][M: 100 B: 14 C: 11]]", howmuch(1, 100))
        assertEquals("[[M: 37 B: 5 C: 4][M: 100 B: 14 C: 11][M: 163 B: 23 C: 18]]", howmuch(0, 200))
    }
}
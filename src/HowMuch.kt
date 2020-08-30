import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

fun howmuch(m: Int, n: Int): String {
    val result = if (m < n) {
        (m..n)
            .filter { money -> (money - 2).rem(7) == 0 && (money - 1).rem(9) == 0 }
            .joinToString("") { "[M: $it B: ${it / 7} C: ${it / 9}]" }
    } else {
        (n..m)
            .filter { money -> (money - 2).rem(7) == 0 && (money - 1).rem(9) == 0 }
            .joinToString("") { "[M: $it B: ${it / 7} C: ${it / 9}]" }
    }

    return "[$result]"
}

class CarboatTest {
    @Test
    fun BasicTests() {
        println("****** Basic tests ******")
        assertEquals("[[M: 37 B: 5 C: 4][M: 100 B: 14 C: 11]]", howmuch(1, 100))
        assertEquals("[[M: 37 B: 5 C: 4][M: 100 B: 14 C: 11][M: 163 B: 23 C: 18]]", howmuch(0, 200))
        assertEquals("[[M: 9991 B: 1427 C: 1110]]", howmuch(0, 1000))
    }
}
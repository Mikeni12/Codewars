import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.test.assertEquals

/*
Your task is to construct a building which will be a pile of n cubes. The cube at the bottom will have a volume of n^3,
the cube above will have volume of (n-1)^3 and so on until the top which will have a volume of 1^3.

You are given the total volume m of the building. Being given m can you find the number n of cubes you will have to build?

The parameter of the function findNb (find_nb, find-nb, findNb) will be an integer m and you have to return
the integer n such as n^3 + (n-1)^3 + ... + 1^3 = m if such a n exists or -1 if there is no such n.

Examples:

findNb(1071225) --> 45
findNb(91716553919377) --> -1
mov rdi, 1071225
call find_nb            ; rax <-- 45

mov rdi, 91716553919377
call find_nb            ; rax <-- -1
 */

object ASum {
    fun findNb(m: Long): Long {
        var count = 1L
        var total = 0L
        while (total <= m) {
            total += count * count * count++
            if (total == m) {
                count--
                break
            }
            if (total > m) {
                count = -1
                break
            }
        }
        return count
    }
}


class ASumTest {
    private fun testing(n: Long, expected: Long) {
        var actual = ASum.findNb(n)
        assertEquals(expected, actual)
    }

    @Test
    fun fixedTests() {
        testing(56396345062501, -1)
        testing(6132680780625, 2225)
        testing(1071225, 45)
        testing(28080884739601, -1)
    }
}
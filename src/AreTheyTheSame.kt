import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
Given two arrays a and b write a function comp(a, b) (compSame(a, b) in Clojure) that checks whether the two arrays
have the "same" elements, with the same multiplicities. "Same" means, here, that the elements in b are the elements in
a squared, regardless of the order.

Examples

Valid arrays

a = [121, 144, 19, 161, 19, 144, 19, 11]
b = [121, 14641, 20736, 361, 25921, 361, 20736, 361]
comp(a, b) returns true because in b 121 is the square of 11, 14641 is the square of 121, 20736 the square of 144, 361
the square of 19, 25921 the square of 161, and so on. It gets obvious if we write b's elements in terms of squares:

a = [121, 144, 19, 161, 19, 144, 19, 11]
b = [11*11, 121*121, 144*144, 19*19, 161*161, 19*19, 144*144, 19*19]
Invalid arrays

If we change the first number to something else, comp may not return true anymore:

a = [121, 144, 19, 161, 19, 144, 19, 11]
b = [132, 14641, 20736, 361, 25921, 361, 20736, 361]
comp(a,b) returns false because in b 132 is not the square of any number of a.

a = [121, 144, 19, 161, 19, 144, 19, 11]
b = [121, 14641, 20736, 36100, 25921, 361, 20736, 361]
comp(a,b) returns false because in b 36100 is not the square of any number of a.

Remarks

a or b might be [] (all languages except R, Shell).
a or b might be nil or null or None or nothing (except in Haskell, Elixir, C++, Rust, R, Shell, PureScript).
If a or b are nil (or null or None), the problem doesn't make sense so return false.

Note for C

The two arrays have the same size (> 0) given as parameter in function comp.
 */

fun comp(a: IntArray?, b: IntArray?): Boolean =
    if (a != null && b != null) {
        when {
            a.size != b.size -> false
            a.isEmpty() && b.isEmpty() -> true
            else -> b.map { it.toDouble() }.toMutableList().check(a.map { it.toDouble() }.toMutableList())
        }
    } else {
        false
    }

fun MutableList<Double>.check(a: MutableList<Double>): Boolean =
    if (this.isEmpty() && a.isEmpty()) true else {
        if (a.contains(Math.sqrt(this.first()))) {
            a.removeAt(a.indexOf(Math.sqrt(this.first())))
            this.apply { removeAt(0) }.check(a)
        } else {
            false
        }
    }


class TestArrays {
    @Test
    fun testFixed() {
        val a1 = intArrayOf(121, 144, 19, 161, 19, 144, 19, 11)
        val a2 = intArrayOf(11 * 11, 121 * 121, 144 * 144, 19 * 19, 161 * 161, 19 * 19, 144 * 144, 19 * 19)
        val b1 = intArrayOf(121, 144, 19, 161, 19, 144, 19, 11)
        val b2 = intArrayOf(121, 14641, 20736, 361, 25921, 361, 20736, 361)
        val c1 = intArrayOf(121, 144, 19, 161, 19, 144, 19, 11)
        val c2 = intArrayOf(121, 14641, 20736, 36100, 25921, 361, 20736, 361)
        val d1 = intArrayOf(10001, 10000)
        val d2 = intArrayOf(100000000, 100000000)
        val e1 = intArrayOf(61,1,99,83,85,54,54,75,61)
        val e2 = intArrayOf(1,2916,2917,3721,3721,5625,6889,7225,9801)

        assertEquals(true, comp(a1, a2))
        assertEquals(true, comp(b1, b2))
        assertEquals(false, comp(c1, c2))
        assertEquals(false, comp(d1, d2))
        assertEquals(false, comp(e1, e2))
    }
}
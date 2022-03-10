import org.junit.jupiter.api.Test
import kotlin.math.sign
import kotlin.test.assertEquals

/*
The maximum sum subarray problem consists in finding the maximum sum of a contiguous subsequence in an
array or list of integers:

maxSequence(listOf(-2, 1, -3, 4, -1, 2, 1, -5, 4));
// should be 6: listOf(4, -1, 2, 1)
Easy case is when the list is made up of only positive numbers and the maximum sum is the sum of the whole array.
If the list is made up of only negative numbers, return 0 instead.

Empty list is considered to have zero greatest sum. Note that the empty list or array is also a valid sublist/subarray.
*/

fun maxSequence(arr: List<Int>): Int = (1..arr.size)
    .flatMap { arr.windowed(it) }
    .maxByOrNull { it.sum() }?.sum()
    ?.run { if (this > 1) this else 0 } ?: 0


class ExampleTestCases {

    @Test
    fun `it should work on an empty list`() {
        assertEquals(0, maxSequence(emptyList()))
    }

    @Test
    fun `it should work on the example`() {
        assertEquals(6, maxSequence(listOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    }

    @Test
    fun `it should work with negative numbers`() {
        assertEquals(0, maxSequence(listOf(-2, -1, -3)))
    }

}

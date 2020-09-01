import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
Task

You've just moved into a perfectly straight street with exactly n identical houses on either side of the road.
Naturally, you would like to find out the house number of the people on the other side of the street.
The street looks something like this:

Street

1|   |6
3|   |4
5|   |2
Evens increase on the right; odds decrease on the left. House numbers start at 1 and increase without gaps.
When n = 3, 1 is opposite 6, 3 opposite 4, and 5 opposite 2.

Example

Given your house number address and length of street n, give the house number on the opposite side of the street.

overTheRoad(address, n)
overTheRoad(1, 3) = 6
overTheRoad(3, 3) = 4
overTheRoad(2, 3) = 5
overTheRoad(3, 5) = 8
Both n and address could get upto 500 billion with over 200 random tests.
 */

fun overTheRoad(address: Int, n: Int): Int = n * 2 - (address - 1)

class Tests {
    @Test
    fun Examples() {
        println("(1,3) --> 6")
        assertEquals(overTheRoad(1, 3), 6)
        println("(3,3) --> 4")
        assertEquals(overTheRoad(3, 3), 4)
        println("(2,3) --> 5")
        assertEquals(overTheRoad(2, 3), 5)
        println("(3,5) --> 8")
        assertEquals(overTheRoad(3, 5), 8)
        println("(7,11) --> 16")
        assertEquals(overTheRoad(7, 11), 16)
        println("(20,1000000) -->1999981")
        assertEquals(overTheRoad(20, 1000000), 1999981)
    }
}
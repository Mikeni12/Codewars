import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

/*
You have an amount of money a0 > 0 and you deposit it with a constant interest rate of p% > 0 per year on the 1st of January 2016. You want to have an amount a >= a0.

Task:

The function date_nb_days (or dateNbDays...) with parameters a0, a, p will return, as a string, the date on which you have just reached a.

Example:

date_nb_days(100, 101, 0.98) --> "2017-01-01" (366 days)

date_nb_days(100, 150, 2.00) --> "2035-12-26" (7299 days)

Notes:

The return format of the date is "YYYY-MM-DD"
The deposit is always on the "2016-01-01"
If p% is the rate for a year the rate for a day is p divided by 36000 since banks consider that there are 360 days in a year.
You have: a0 > 0, p% > 0, a >= a0
 */

fun dateNbDays(a0: Double, a: Double, p: Double): String = LocalDate
    .of(2016, 1, 1)
    .plusDays(a0.calculate(p.div(36000), a, 0))
    .toString()

fun Double.calculate(percentage: Double, total: Double, days: Int): Long =
    if (this < total) (this + (this * percentage)).calculate(percentage, total, days + 1) else days.toLong()


class DateDaysTest {
    private fun testing(actual: String, expected: String) {
        assertEquals(expected, actual)
    }

    @Test
    fun test() {
        println("Fixed Tests dateNbDays")
        testing(dateNbDays(4281.0, 5087.0, 2.0), "2024-07-03")
        testing(dateNbDays(4620.0, 5188.0, 2.0), "2021-09-19")

    }
}

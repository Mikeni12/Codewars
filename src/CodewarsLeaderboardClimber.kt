import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
We all want to climb the leaderboard. Even given some of the massive scores on there, it's nice to know how close you are...

In this kata you will be given a username and their score, your score (not your real score) and you need to calculate how many kata you need to complete to beat their score, by 1 point exactly.

As this is the easy version (harder one to folow at some point in the future), let's assume only Beta kata and 8kyu kata are available. Worth 3 and 1 point respectively.

Return a string like this: 'To beat "user"'s score, I must complete "x" Beta kata and "y" 8kyu kata.'.

If the total number of kata you need to complete > 1000, add 'Dammit!' to the end of the string also - like so:

'To beat "user"'s score, I must complete 'x' Beta kata and 'y' 8kyu kata. Dammit!'.

If your score is higher than the user's already, return 'Winning!'. If they are equal, return 'Only need one!'.

You are looking to complete as few kata as possible to get to your target
 */

object CodewarsLeaderboardClimber {
    fun leaderBoard(user: String, userScore: Int, yourScore: Int): String {
        return when {
            yourScore > userScore -> "Winning!"
            yourScore == userScore -> "Only need one!"
            else -> {
                val score = userScore - yourScore
                val x = score.div(3)
                val y = score.rem(3)
                val dammit = if (x + y > 1000) " Dammit!" else ""
                "To beat $user's score, I must complete $x Beta kata and $y 8kyu kata.$dammit"
            }
        }
    }
}

class CodewarsLeaderboardClimberTest {
    @Test
    fun basicTests() {
        assertEquals(
            "To beat g964's score, I must complete 5365 Beta kata and 2 8kyu kata. Dammit!",
            CodewarsLeaderboardClimber.leaderBoard("g964", 36097, 20000)
        )
        assertEquals(
            "To beat GiacomoSorbi's score, I must complete 15 Beta kata and 2 8kyu kata.",
            CodewarsLeaderboardClimber.leaderBoard("GiacomoSorbi", 23914, 23867)
        )
        assertEquals(
            "To beat ZozoFouchtra's score, I must complete 1330 Beta kata and 1 8kyu kata. Dammit!",
            CodewarsLeaderboardClimber.leaderBoard("ZozoFouchtra", 15991, 12000)
        )
    }
}
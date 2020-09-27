import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
John keeps a backup of his old personal phone book as a text file. On each line of the file he can find the phone number
(formated as +X-abc-def-ghij where X stands for one or two digits), the corresponding name between < and > and the
address.

Unfortunately everything is mixed, things are not always in the same order; parts of lines are cluttered with
non-alpha-numeric characters (except inside phone number and name).

Examples of John's phone book lines:

"/+1-541-754-3010 156 Alphand_St. <J Steeve>\n"

" 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010!\n"

"<Anastasia> +48-421-674-8974 Via Quirinal Roma\n"

Could you help John with a program that, given the lines of his phone book and a phone number num returns a string
for this number : "Phone => num, Name => name, Address => adress"

Examples:

s = "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010!\n"

phone(s, "1-541-754-3010") should return "Phone => 1-541-754-3010, Name => J Steeve, Address => 156 Alphand St."
It can happen that there are many people for a phone number num, then

return : "Error => Too many people: num"

or it can happen that the number num is not in the phone book, in that case

return: "Error => Not found: num"

You can see other examples in the test cases.

JavaScript random tests completed by @matt c

Note

Codewars stdout doesn't print part of a string when between < and >
 */

object PhoneDir {

    fun phone(strng: String, num: String): String {
        val contacts = strng.split("\n").filter { it.contains("+$num") }.map {
            val name = it.substringAfter("<").substringBefore(">")
            val address = it.replace("($name|$num|[^a-zA-Z0-9.-])".toRegex(), " ").replace("\\s+".toRegex(), " ").trim()
            "Phone => $num, Name => $name, Address => $address"
        }
        return when {
            contacts.isEmpty() -> "Error => Not found: $num"
            contacts.count() > 1 -> "Error => Too many people: $num"
            else -> contacts.first()
        }
    }
}

class PhoneDirTest {
    private fun testing(actual: String, expected: String) {
        assertEquals(expected, actual)
    }

    val dr = ("/+1-541-754-3010 156 Alphand_St. <J Steeve>\n 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n"
            + "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n <P Salinger> Main Street, +1-098-512-2222, Denver\n"
            + "<Q Salinge> Main Street, +1-098-512-2222, Denve\n" + "<R Salinge> Main Street, +1-098-512-2222, Denve\n"
            + "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n")

    @Test
    fun test1() {
        testing(
            PhoneDir.phone(dr, "48-421-674-8974"),
            "Phone => 48-421-674-8974, Name => Anastasia, Address => Via Quirinal Roma"
        )
        testing(
            PhoneDir.phone(dr, "19-421-674-8974"),
            "Phone => 19-421-674-8974, Name => C Powel, Address => Chateau des Fosses Strasbourg F-68000"
        )
        testing(PhoneDir.phone(dr, "1-098-512-2222"), "Error => Too many people: 1-098-512-2222")
        testing(PhoneDir.phone(dr, "5-555-555-5555"), "Error => Not found: 5-555-555-5555")

    }
}
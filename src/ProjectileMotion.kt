import org.junit.jupiter.api.Test
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.test.assertEquals

/*
Task:

You must create a class, Projectile, which takes in 3 arguments when initialized: starting height (0 ≤ h0 < 200),
starting velocity (0 < v0 < 200), and angle of the projectile when it is released (0º < a < 90º, measured in degrees).
All units for distance are feet, and units for time are seconds.

NOTE: Some solutions were invalidated because I added tests for situations where the starting height is 0, in
which case the equation for height would be in the form h(t) = -16.0t^2 + vt where v represents the initial
vertical velocity.

Projectile Motion Equation for reference

In the above equation, h represents the height of the projectile after t seconds; v represents the initial vertical
velocity; and h0 represents the starting height.

You need to write the following methods for the Projectile class. In Crystal, the arguments passed when the object is
initialized will always be of the type Float64, and in Java/Scala/Kotlin/Dart/C#, they will be int/Ints.

A height_eq, heightEq, or HeightEq method, which returns the equation for height of the projectile as a function of time. [takes in nothing, returns a String]
A horiz_eq, horizEq, or HeightEq method, which returns the equation for the horizontal position of the projectile as a function of time. [takes in nothing, returns a String]
A height or Height method, which takes in an argument t and calculates the height of the projectile in feet. [takes in a double, returns a double]
A horiz or Horiz method, which also takes in an argument t and calculates the horizontal distance that the projectile has traveled. [takes in a double, returns a double]
A landing or Landing method which returns the point at which the projectile lands on the ground, in the form [x, y, t]. (y should always be 0). [takes in nothing, returns an array of doubles]
EVERYTHING, including values in the equations appearing as coefficients, must be rounded to THREE decimal places. However, if the value is whole, only show one decimal place (for example => -16 becomes -16.0, not -16.000). But ensure that you DO NOT use the three-decimal-place rounded values for calculations. Otherwise, you will find yourself getting answers CLOSE to the correct one but slightly off.

You also need to define instance variables as needed. These will not be tested.

Examples:

Projectile Motion Example This example shows the initial vertical and horizontal velocity when a projectile is fired at 2 ft/s.

var p = Projectile(5, 2, 45); //=> a projectile starting at 5 ft above the ground, traveling initially at 2 ft/s,
and at an angle of 45 degrees with the horizontal (shown in the triangle above)
p.heightEq(); //=> "h(t) = -16.0t^2 + 1.414t + 5.0"
  # 1.414 = 2sin(45º)
p.horizEq(); //=> "x(t) = 1.414t"
  # 1.414 = 2cos(45º)
p.height(0.2); //=> 4.643 (Calculation: -16(0.2)^2 + (2sin(45º))(0.2) + 5)
p.horiz(0.2); //=> 0.283 (Calculation: 2cos(45º) * 0.2)
p.landing(); //=> doubleArrayOf(0.856, 0, 0.605) (After 0.605 seconds (t = 0.605), the particle has landed on the ground (y = 0), and is 0.856 ft horizontally (x = 0.856) away from the release point.)

Additionally, note that all coefficients are to be expressed as floats in the string equations, regardless of whether
or not they are whole. This means that whole numbers should always be formatted with a ".0" appended.
 */

class Projectile(val height: Int, val v0: Int, val angle: Int) {

    val v = v0 * Math.sin(angle * Math.PI / 180)
    val h = v0 * Math.cos(angle * Math.PI / 180)
    val hString = if (height == 0) "" else " + $height.0"

    fun heightEq(): String = "h(t) = -16.0t^2 + ${v.roundOffDecimal()}t$hString"

    fun horizEq(): String = "x(t) = ${h.roundOffDecimal()}t"

    fun height(time: Double): Double = (-16 * Math.pow(time, 2.0) + v * time + height).roundOffDecimal()

    fun horiz(time: Double): Double = (h * time).roundOffDecimal()

    fun landing(): DoubleArray {
        val t = (-v - Math.sqrt(Math.pow(v, 2.0) - 4 * (-16) * height)) / (2 * (-16))
        return doubleArrayOf(horiz(t).roundOffDecimal(), 0.0, t.roundOffDecimal())
    }

    fun Double.roundOffDecimal(): Double {
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.HALF_UP
        return df.format(this).toDouble()
    }
}

class ExampleTests {
    @Test
    fun description() {
        var p = Projectile(5, 2, 45)
        assertEquals("h(t) = -16.0t^2 + 1.414t + 5.0", p.heightEq());
        assertEquals("x(t) = 1.414t", p.horizEq())
        assertEquals(4.643, p.height(0.2))
        assertEquals(0.283, p.horiz(0.2))
        assertEquals(0.856, p.landing()[0])
        assertEquals(0.0, p.landing()[1])
        assertEquals(0.605, p.landing()[2])
    }

    @Test
    fun more() {
        var p = Projectile(0, 5, 45);
        assertEquals("h(t) = -16.0t^2 + 3.536t", p.heightEq())
        assertEquals("x(t) = 3.536t", p.horizEq())
        assertEquals(0.067, p.height(0.2))
        assertEquals(0.707, p.horiz(0.2))
        assertEquals(0.781, p.landing()[0])
        assertEquals(0.0, p.landing()[1])
        assertEquals(0.221, p.landing()[2])
    }

    @Test
    fun more_() {
        var p = Projectile(15, 12, 80)
        assertEquals("h(t) = -16.0t^2 + 11.818t + 15.0", p.heightEq())
        assertEquals("x(t) = 2.084t", p.horizEq())
        assertEquals(15.0, p.height(0.0))
        assertEquals(0.0, p.horiz(0.0))
        assertEquals(10.818, p.height(1.0))
        assertEquals(2.084, p.horiz(1.0))
        assertEquals(2.929, p.landing()[0])
        assertEquals(0.0, p.landing()[1])
        assertEquals(1.406, p.landing()[2])
    }
}
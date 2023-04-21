package behavioural

import org.junit.jupiter.api.Test

class Printer(private val stringFormatterStrategyTest: (String) -> String) {
    fun printString(message: String) {
        println(stringFormatterStrategyTest(message))
    }
}

val lowercaseFormatter = { it: String -> it.lowercase() }
val uppercaseFormatter = { it: String -> it.uppercase() }

class StrategyTest {
    @Test
    fun testStrategy() {
        val inputString = "LOREM ipsum DOLOR sit amet"

        val lowercasePrinter = Printer(lowercaseFormatter)
        lowercasePrinter.printString(inputString)

        val uppercasePrinter = Printer(uppercaseFormatter)
        uppercasePrinter.printString(inputString)
    }
}
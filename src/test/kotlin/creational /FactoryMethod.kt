package `creational `

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

sealed class Country {
    object Canada : Country()
}

object Spain : Country()
class Greece(val someProperty: String) : Country()
data class USA(val someProperty: String) : Country()

class Currency(
    val code: String
)

object CurrencyFactory {
    fun currencyForCountry(country: Country): Currency =
        when (country) {
            is Spain -> Currency("EUR")
            is Greece -> Currency("EUR")
            is USA -> Currency("USD")
            else -> Currency("CAD")
        }
}

class FactoryMethodTest {
    @Test
    fun currencyTest() {
        CurrencyFactory.currencyForCountry(Greece("")).code.let {
            println("Greek currency: $it")
            Assertions.assertThat(it).isEqualTo("EUR")

        }

        CurrencyFactory.currencyForCountry(USA("")).code.let {
            println("Usa currency: $it")
            Assertions.assertThat(it).isEqualTo("USD")
        }
    }
}
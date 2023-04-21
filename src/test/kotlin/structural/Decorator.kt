package structural

import org.junit.jupiter.api.Test

interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine : CoffeeMachine {
    override fun makeSmallCoffee() {
        println("Normal coffee machine: Making small coffee")
    }

    override fun makeLargeCoffee() {
        println("Normal coffee machine: Making large coffee")
    }
}

// Decorator

class EnhanceCoffeeMachine(private val coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine {
    // overriding behaviour
    override fun makeLargeCoffee() {
        println("Enhanced coffee machine: Making large coffee")
    }

    fun makeMilkCoffee() {
        println("Enhanced coffee machine: Making milk coffee")
        coffeeMachine.makeSmallCoffee()
        println("Enhanced coffee machine: Adding milk")
    }
}

class DecoratorTest {
    @Test
    fun testDecorator() {
        val normalMachine = NormalCoffeeMachine()
        val enhancedMachine = EnhanceCoffeeMachine(normalMachine)

        enhancedMachine.makeSmallCoffee()
        println("---------------------")
        enhancedMachine.makeLargeCoffee()
        println("---------------------")
        enhancedMachine.makeMilkCoffee()
    }
}
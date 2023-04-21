package behavioural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

interface ReportElement {
    fun <R> accept(visitor: ReportVisitor<R>): R
}

class FixedPriceContract(val constPerYear: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

class TimeAndMaterialsContract(val constPerHour: Long, val hours: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

class SupportContract(val constPerMonth: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

interface ReportVisitor<out R> {
    fun visit(contract: FixedPriceContract): R
    fun visit(contract: TimeAndMaterialsContract): R
    fun visit(contract: SupportContract): R
}

class MonthlyCostReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract): Long = contract.constPerYear / 12

    override fun visit(contract: TimeAndMaterialsContract): Long = contract.constPerHour * contract.hours

    override fun visit(contract: SupportContract): Long = contract.constPerMonth

}

class YearlyCostReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract): Long = contract.constPerYear

    override fun visit(contract: TimeAndMaterialsContract): Long = contract.constPerHour * contract.hours

    override fun visit(contract: SupportContract): Long = contract.constPerMonth / 12

}

class VisitorTest {
    @Test
    fun testVisitor() {
        val projectAlpha = FixedPriceContract(10_000)
        val projectBeta = SupportContract(500)
        val projectGama = TimeAndMaterialsContract(150, 10)
        val projectKappa = TimeAndMaterialsContract(50, 50)


        val projects = arrayListOf(projectAlpha, projectGama, projectBeta, projectKappa)

        val monthlyCostReportVisitor = MonthlyCostReportVisitor()
        val monthlyCost = projects.sumOf { it.accept(monthlyCostReportVisitor) }
        println("Monthly cost: $monthlyCost")
        Assertions.assertThat(monthlyCost).isEqualTo(5333)

        val yearlyCostReportVisitor = YearlyCostReportVisitor()
        val yearlyCost = projects.sumOf { it.accept(yearlyCostReportVisitor) }
        println("Monthly cost: $yearlyCost")
        Assertions.assertThat(yearlyCost).isEqualTo(20_000)
    }
}
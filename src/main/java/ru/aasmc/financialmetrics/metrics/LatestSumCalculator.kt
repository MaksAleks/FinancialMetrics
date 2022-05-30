package ru.aasmc.financialmetrics.metrics

import ru.aasmc.financialmetrics.filters.Filter
import ru.aasmc.financialmetrics.model.Instrument
import java.util.*

class LatestSumCalculator(
    val name: String,
    override val filters: List<Filter>,
    private val numMonths: Int
) : Metric<MetricResult.LatestSum> {

    private var results: SortedSet<Instrument> = TreeSet(Comparator.reverseOrder())

    override fun calculateResult(): MetricResult.LatestSum {
        return MetricResult.LatestSum(
            amount = results.sumOf { it.amount },
            name = name
        )
    }

    override fun processInstrument(instrument: Instrument) {
        if (filters.all { it.check(instrument) }) {
            results.add(instrument)
        }
        if (results.size > numMonths) {
            results = TreeSet(results.headSet(results.last()))
        }
    }
}
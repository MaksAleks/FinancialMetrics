package ru.aasmc.financialmetrics.metrics

import ru.aasmc.financialmetrics.filters.Filter
import ru.aasmc.financialmetrics.model.Instrument

class AveragePriceCalculator(
    val name: String,
    override val filters: List<Filter>
): Metric<MetricResult.AveragePrice> {
    private var count = 0
    private var totalPrice = 0.0

    override fun processInstrument(instrument: Instrument) {
        if (filters.all { it.check(instrument) }) {
            ++count
            totalPrice += instrument.amount
        }
    }

    override fun calculateResult(): MetricResult.AveragePrice {
        return MetricResult.AveragePrice(
            amount = totalPrice / count,
            name = name
        )
    }
}
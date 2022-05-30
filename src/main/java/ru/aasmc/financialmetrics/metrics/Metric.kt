package ru.aasmc.financialmetrics.metrics

import ru.aasmc.financialmetrics.filters.Filter
import ru.aasmc.financialmetrics.model.Instrument

interface Metric<out T : MetricResult> {
    val filters: List<Filter>
    fun processInstrument(instrument: Instrument)
    fun calculateResult(): T
}
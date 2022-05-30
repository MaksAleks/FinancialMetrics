package ru.aasmc.financialmetrics.reader

import ru.aasmc.financialmetrics.metrics.MetricResult


interface InstrumentReader{
    fun process(): List<MetricResult>
}
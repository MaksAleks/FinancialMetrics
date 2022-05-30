package ru.aasmc.financialmetrics.metrics

sealed class MetricResult {

    data class AveragePrice( val name: String, val amount: Double): MetricResult()
    data class LatestSum(val name: String, val amount: Double): MetricResult()
}

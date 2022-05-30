package ru.aasmc.financialmetrics

import ru.aasmc.financialmetrics.filters.DateFilter
import ru.aasmc.financialmetrics.filters.Filter
import ru.aasmc.financialmetrics.filters.InstrumentNameFilter
import ru.aasmc.financialmetrics.filters.WorkDayFilter
import ru.aasmc.financialmetrics.metrics.AveragePriceCalculator
import ru.aasmc.financialmetrics.metrics.LatestSumCalculator
import ru.aasmc.financialmetrics.metrics.MetricResult
import ru.aasmc.financialmetrics.reader.InstrumentReader
import ru.aasmc.financialmetrics.reader.InstrumentReaderImpl

const val INSTRUMENT1 = "INSTRUMENT1"
const val INSTRUMENT2 = "INSTRUMENT2"
const val INSTRUMENT3 = "INSTRUMENT3"

fun main() {
    val instrumentOneFilters = listOf<Filter>(
        InstrumentNameFilter.INSTRUMENT_ONE_FILTER,
        WorkDayFilter.WORK_DAYS_FILTER
    )

    val instrumentTwoFilters = listOf<Filter>(
        WorkDayFilter.WORK_DAYS_FILTER,
        InstrumentNameFilter.INSTRUMENT_TWO_FILTER,
        DateFilter.NOVEMBER_FILTER
    )

    val instrumentThreeFilters = listOf<Filter>(
        WorkDayFilter.WORK_DAYS_FILTER,
        InstrumentNameFilter.INSTRUMENT_THREE_FILTER
    )

    val instrumentOneMetric = AveragePriceCalculator(
        name = INSTRUMENT1,
        instrumentOneFilters
    )

    val instrumentTwoMetric = AveragePriceCalculator(
        name = INSTRUMENT2,
        instrumentTwoFilters
    )

    val instrumentThreeMetric = LatestSumCalculator(
        name = INSTRUMENT3,
        filters = instrumentThreeFilters,
        numMonths = 10
    )

    val reader: InstrumentReader = InstrumentReaderImpl(
        "instruments.txt",
        listOf(
            instrumentOneMetric,
            instrumentTwoMetric,
            instrumentThreeMetric
        )
    )
    val result = reader.process()
    result.forEach { metricResult ->
        when(metricResult) {
            is MetricResult.AveragePrice -> {
                println("Average price for ${metricResult.name} = ${metricResult.amount}")
            }
            is MetricResult.LatestSum -> {
                println("Latest sum for ${metricResult.name} = ${metricResult.amount}")
            }
        }
    }

}
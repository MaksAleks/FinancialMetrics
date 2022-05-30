package ru.aasmc.financialmetrics.reader

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.aasmc.financialmetrics.filters.DateFilter
import ru.aasmc.financialmetrics.filters.Filter
import ru.aasmc.financialmetrics.filters.InstrumentNameFilter
import ru.aasmc.financialmetrics.filters.WorkDayFilter
import ru.aasmc.financialmetrics.metrics.AveragePriceCalculator
import ru.aasmc.financialmetrics.metrics.LatestSumCalculator
import ru.aasmc.financialmetrics.metrics.MetricResult

internal class InstrumentReaderImplTest {

    lateinit var reader: InstrumentReader


    @BeforeEach
    fun setup() {
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
            name = "INSTRUMENT1",
            instrumentOneFilters
        )

        val instrumentTwoMetric = AveragePriceCalculator(
            name = "INSTRUMENT2",
            instrumentTwoFilters
        )

        val instrumentThreeMetric = LatestSumCalculator(
            name = "INSTRUMENT3",
            filters = instrumentThreeFilters,
            numMonths = 10
        )
        reader = InstrumentReaderImpl(
            pathToFile = "test_input.txt",
            listOf(
                instrumentOneMetric,
                instrumentTwoMetric,
                instrumentThreeMetric
            )
        )
    }

    @Test
    fun testInstrumentOne() {
        val process = reader.process()
        val result = process.filter { it is MetricResult.AveragePrice && it.name == "INSTRUMENT1" }
        assertTrue(result.size == 1)
        val metricResult = result[0] as MetricResult.AveragePrice
        assertTrue(metricResult.amount == 2.0)
    }

    @Test
    fun testInstrumentTwo() {
        val process = reader.process()
        val result = process.filter { it is MetricResult.AveragePrice && it.name == "INSTRUMENT2" }
        assertTrue(result.size == 1)
        val metricResult = result[0] as MetricResult.AveragePrice
        assertTrue(metricResult.amount == 2.0)
    }

    @Test
    fun testInstrumentThree() {
        val process = reader.process()
        val result = process.filter { it is MetricResult.LatestSum && it.name == "INSTRUMENT3" }
        assertTrue(result.size == 1)
        val metricResult = result[0] as MetricResult.LatestSum
        assertEquals(20.0,metricResult.amount)
    }

}
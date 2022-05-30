package ru.aasmc.financialmetrics.filters

import ru.aasmc.financialmetrics.model.Instrument
import java.time.LocalDate
import java.time.Month

class DateFilter(
    private val start: LocalDate,
    private val end: LocalDate
) : Filter {
    override fun check(instrument: Instrument): Boolean {
        return instrument.date.isAfter(start) && instrument.date.isBefore(end)
    }

    companion object {
        val NOVEMBER_FILTER = DateFilter(
            start = LocalDate.of(2014, Month.NOVEMBER, 1),
            end = LocalDate.of(2014, Month.NOVEMBER, 30)
        )
    }
}
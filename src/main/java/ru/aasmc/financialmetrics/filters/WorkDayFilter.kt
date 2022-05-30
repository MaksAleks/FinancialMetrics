package ru.aasmc.financialmetrics.filters

import ru.aasmc.financialmetrics.model.Instrument

class WorkDayFilter : Filter {
    override fun check(instrument: Instrument): Boolean {
        return instrument.date.dayOfWeek.value in 1..5
    }

    companion object {
        val WORK_DAYS_FILTER = WorkDayFilter()
    }
}
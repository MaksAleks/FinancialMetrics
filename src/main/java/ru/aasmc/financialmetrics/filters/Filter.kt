package ru.aasmc.financialmetrics.filters

import ru.aasmc.financialmetrics.model.Instrument

interface Filter {
    fun check(instrument: Instrument): Boolean
}

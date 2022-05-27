package ru.aasmc.financialmetrics.filters

import ru.aasmc.financialmetrics.INSTRUMENT1
import ru.aasmc.financialmetrics.INSTRUMENT2
import ru.aasmc.financialmetrics.INSTRUMENT3
import ru.aasmc.financialmetrics.model.Instrument

class InstrumentNameFilter(
    private val nameToCheck: String
) : Filter {
    override fun check(instrument: Instrument): Boolean {
        return nameToCheck == instrument.name
    }

    companion object {
        val INSTRUMENT_ONE_FILTER = InstrumentNameFilter(
            nameToCheck = INSTRUMENT1
        )
        val INSTRUMENT_TWO_FILTER = InstrumentNameFilter(
            nameToCheck = INSTRUMENT2
        )
        val INSTRUMENT_THREE_FILTER = InstrumentNameFilter(
            nameToCheck = INSTRUMENT3
        )
    }
}
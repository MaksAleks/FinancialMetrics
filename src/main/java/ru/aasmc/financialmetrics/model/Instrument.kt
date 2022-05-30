package ru.aasmc.financialmetrics.model

import ru.aasmc.financialmetrics.util.formatter
import java.time.LocalDate

data class Instrument(
    val name: String,
    val date: LocalDate,
    val amount: Double
): Comparable<Instrument> {
    companion object {
        fun fromString(instrumentStr: String): Instrument {
            val split = instrumentStr.split(",")
            return Instrument(
                name = split[0],
                date = LocalDate.parse(split[1], formatter),
                amount = split[2].toDouble()
            )
        }
    }

    override fun compareTo(other: Instrument): Int {
        return this.date.compareTo(other.date)
    }
}

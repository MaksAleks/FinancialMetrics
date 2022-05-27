package ru.aasmc.financialmetrics.reader

import ru.aasmc.financialmetrics.metrics.Metric
import ru.aasmc.financialmetrics.metrics.MetricResult
import ru.aasmc.financialmetrics.model.Instrument
import java.nio.file.Files
import java.nio.file.Paths

class InstrumentReaderImpl(
    pathToFile: String,
    private val metrics: List<Metric<*>>,
) : InstrumentReader {

    private val readerStream = Files.lines(Paths.get(pathToFile))

    override fun process(): List<MetricResult> {
        val result: MutableList<MetricResult> = mutableListOf()
        readerStream.forEach {
            val instrument = Instrument.fromString(it)
            metrics.forEach { metric ->
                metric.processInstrument(instrument)
            }
        }
        metrics.forEach { metric ->
            result.add(metric.calculateResult())
        }
        return result
    }
}
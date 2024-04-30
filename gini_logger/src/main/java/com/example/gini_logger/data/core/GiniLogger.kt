package com.example.gini_logger.data.core

import com.example.gini_logger.data.default_implementation.DefaultFormatter
import com.example.gini_logger.data.default_implementation.DefaultLogBuilderProvider
import com.example.gini_logger.data.default_implementation.DefaultLoggerProvider
import com.example.gini_logger.data.default_implementation.DefaultTag
import com.example.gini_logger.data.default_implementation.DefaultWritingMode
import com.example.gini_logger.domain.Formatter
import com.example.gini_logger.domain.LogBuilder
import com.example.gini_logger.domain.LogBuilderProvider
import com.example.gini_logger.domain.LoggerProvider
import com.example.gini_logger.domain.WritingMode
import com.example.gini_logger.domain.model.Level

object GiniLogger {

    private var logManager: LogManager<WritingMode, LogBuilder>? = null

    private val nonNullableLogManager: LogManager<WritingMode, LogBuilder>
        get() = logManager ?: throw RuntimeException(
            "GiniLogger has not been initialized. Must call initialize() or initializeDefault() functions"
        )

    internal val minLevel: Level get() = nonNullableLogManager.minLevel

    fun <W : WritingMode, B : LogBuilder> initialize(
        minLevel: Level,
        writingMode: W,
        loggerProvider: LoggerProvider<W>,
        formatter: Formatter,
        tag: String,
        logBuilderProvider: LogBuilderProvider<B>,
    ) {
        val initializedLogManager = LogManager(
            minLevel = minLevel,
            writingMode = writingMode,
            loggerProvider = loggerProvider,
            formatter = formatter,
            tag = tag,
            logBuilderProvider = logBuilderProvider,
        )

        logManager = initializedLogManager
    }

    fun initializeDefault(
        minLevel: Level = Level.Verbose,
        writingMode: DefaultWritingMode = DefaultWritingMode.Console,
        loggerProvider: LoggerProvider<DefaultWritingMode> = DefaultLoggerProvider,
        formatter: Formatter = DefaultFormatter,
        tag: String = DefaultTag.value,
        logBuilderProvider: LogBuilderProvider<LogBuilder> = DefaultLogBuilderProvider()
    ) {
        val initializedLogManager = LogManager(
            minLevel = minLevel,
            writingMode = writingMode,
            loggerProvider = loggerProvider,
            formatter = formatter,
            tag = tag,
            logBuilderProvider = logBuilderProvider,
        )

        logManager = initializedLogManager
    }

    internal fun log(level: Level, message: String) {
        nonNullableLogManager.log(level = level, message = message)
    }

    internal fun log(level: Level, block: LogBuilder.() -> Unit) {
        nonNullableLogManager.log(level = level, block = block)
    }
}

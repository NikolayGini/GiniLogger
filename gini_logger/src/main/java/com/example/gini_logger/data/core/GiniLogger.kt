package com.example.gini_logger.data.core

import com.example.gini_logger.data.default_implementation.DefaultFormatter
import com.example.gini_logger.data.default_implementation.DefaultLogBuilderProvider
import com.example.gini_logger.data.default_implementation.DefaultTag
import com.example.gini_logger.data.default_implementation.logger.ConsoleLogger
import com.example.gini_logger.domain.Formatter
import com.example.gini_logger.domain.LogBuilder
import com.example.gini_logger.domain.LogBuilderProvider
import com.example.gini_logger.domain.Logger
import com.example.gini_logger.domain.model.Level

object GiniLogger {

    private var logManager: LogManager<LogBuilder>? = null

    private val nonNullableLogManager: LogManager<LogBuilder>
        get() = logManager ?: throw RuntimeException(
            "GiniLogger has not been initialized. Must call initialize() or initializeDefault() functions"
        )

    internal val minLevel: Level get() = nonNullableLogManager.minLevel

    fun initialize(
        minLevel: Level = Level.Verbose,
        logger: Logger = ConsoleLogger,
        formatter: Formatter = DefaultFormatter,
        tag: String = DefaultTag.value,
        logBuilderProvider: LogBuilderProvider<LogBuilder> = DefaultLogBuilderProvider()
    ) {
        val initializedLogManager = LogManager(
            minLevel = minLevel,
            logger = logger,
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

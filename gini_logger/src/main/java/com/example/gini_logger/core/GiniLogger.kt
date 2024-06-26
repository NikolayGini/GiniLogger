package com.example.gini_logger.core

import com.example.gini_logger.data.default_implementation.DefaultFormatter
import com.example.gini_logger.data.default_implementation.DefaultLogBuilderProvider
import com.example.gini_logger.data.default_implementation.DefaultTag
import com.example.gini_logger.data.default_implementation.logger.ConsoleLogger
import com.example.gini_logger.domain.Formatter
import com.example.gini_logger.domain.LogBuilder
import com.example.gini_logger.domain.LogBuilderProvider
import com.example.gini_logger.domain.Logger
import com.example.gini_logger.domain.model.Level

/**
 * A singleton logger object responsible for initializing and managing a [LogManager].
 * It provides a centralized logging utility that can be configured and accessed globally
 * throughout an application.
 *
 * Before using the logging functions, it must be initialized using [initialize]
 * Failing to do so will result in a RuntimeException when trying to log.
 */

object GiniLogger {

    private var logManager: LogManager? = null

    /**
     * Provides a non-nullable instance of [LogManager]. Throws a [RuntimeException] if [GiniLogger] is not initialized.
     */

    private val nonNullableLogManager: LogManager
        get() = logManager ?: throw RuntimeException(
            "GiniLogger has not been initialized. Must call initialize() or initializeDefault() functions"
        )

    /**
     * Gets the minimum logging level set for the current [LogManager].
     */

    internal val minLevel: Level get() = nonNullableLogManager.minLevel

    /**
     * Initializes the [GiniLogger] with a custom configuration. If not called,
     * [GiniLogger] will not perform any logging operations.
     *
     * @param minLevel The minimum level of log messages to be processed.
     * @param logger The logger implementation to use for logging messages.
     * @param formatter The formatter used to format log messages.
     * @param tag A general tag used for all logs from this logger.
     * @param logBuilderProvider A provider for [LogBuilder] instances used for constructing log messages.
     */

    fun initialize(
        minLevel: Level = Level.Verbose,
        logger: Logger = ConsoleLogger,
        formatter: Formatter = DefaultFormatter,
        tag: String = DefaultTag.value,
        logBuilderProvider: LogBuilderProvider = DefaultLogBuilderProvider()
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

    /**
     * Logs a message at the specified level using the configured [LogManager].
     * The message is formatted and logged if the specified level is above or equal to the minimum level.
     *
     * @param level The severity level of the log.
     * @param message The message to log.
     */

    internal fun log(level: Level, message: String) {
        nonNullableLogManager.log(level = level, message = message)
    }

    /**
     * Logs a message constructed using the specified [LogBuilder] lambda at the designated level.
     * This allows for more complex log message construction.
     *
     * @param level The severity level of the log.
     * @param block A lambda block to configure the [LogBuilder].
     */

    internal fun log(level: Level, block: LogBuilder.() -> Unit) {
        nonNullableLogManager.log(level = level, block = block)
    }
}
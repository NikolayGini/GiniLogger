package com.example.gini_logger.data.core

import com.example.gini_logger.domain.Formatter
import com.example.gini_logger.domain.LogBuilder
import com.example.gini_logger.domain.LogBuilderProvider
import com.example.gini_logger.domain.Logger
import com.example.gini_logger.domain.LoggerProvider
import com.example.gini_logger.domain.WritingMode
import com.example.gini_logger.domain.model.Level

internal class LogManager<out W : WritingMode, out B : LogBuilder>(
    val minLevel: Level,
    writingMode: W,
    loggerProvider: LoggerProvider<W>,
    private val formatter: Formatter,
    private val tag: String,
    private val logBuilderProvider: LogBuilderProvider<B>,
) {

    private val logger: Logger = loggerProvider.provide(mode = writingMode)

    fun log(level: Level, message: String) {
        level.performByMinLevel {
            logger.log(
                level = level,
                tag = tag,
                message = formatter.format(message)
            )
        }
    }

    fun log(level: Level, block: LogBuilder.() -> Unit) {
        level.performByMinLevel {
            val logBuilder = logBuilderProvider.provide()

            block(logBuilder)

            logger.log(
                level = level,
                tag = tag,
                message = formatter.format(logBuilder.build())
            )
        }
    }

    private inline fun Level.performByMinLevel(block: () -> Unit) {
        if (ordinal >= minLevel.ordinal) block()
    }
}
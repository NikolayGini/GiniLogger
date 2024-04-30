package com.example.gini_logger.data.default_implementation.logger

import com.example.gini_logger.domain.Logger
import com.example.gini_logger.domain.model.Level

internal class ConsoleAndFileLogger(
    private val fileLogger: FileLogger,
    private val consoleLogger: ConsoleLogger,
) : Logger {

    override fun log(level: Level, tag: String, message: String) {
        fileLogger.log(level = level, tag = tag, message = message)
        consoleLogger.log(level = level, tag = tag, message = message)
    }
}
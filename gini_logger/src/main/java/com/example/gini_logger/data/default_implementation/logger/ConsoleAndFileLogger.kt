package com.example.gini_logger.data.default_implementation.logger

import com.example.gini_logger.domain.Logger
import com.example.gini_logger.domain.model.Level

class ConsoleAndFileLogger(
    private val fileLogger: FileLogger,
) : Logger {

    override fun log(level: Level, tag: String, message: String) {
        fileLogger.log(level = level, tag = tag, message = message)
        ConsoleLogger.log(level = level, tag = tag, message = message)
    }
}
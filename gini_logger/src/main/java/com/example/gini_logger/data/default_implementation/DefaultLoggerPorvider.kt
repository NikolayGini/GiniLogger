package com.example.gini_logger.data.default_implementation

import com.example.gini_logger.data.default_implementation.logger.ConsoleAndFileLogger
import com.example.gini_logger.data.default_implementation.logger.ConsoleLogger
import com.example.gini_logger.data.default_implementation.logger.FileLogger
import com.example.gini_logger.domain.Logger
import com.example.gini_logger.domain.LoggerProvider

object DefaultLoggerProvider : LoggerProvider<DefaultWritingMode> {

    override fun provide(mode: DefaultWritingMode): Logger = when (mode) {
        is DefaultWritingMode.File -> {
            FileLogger(filePath = mode.filePath, fileName = mode.fileName)
        }

        DefaultWritingMode.Console -> ConsoleLogger

        is DefaultWritingMode.ConsoleAndFile -> {
            ConsoleAndFileLogger(
                fileLogger = FileLogger(filePath = mode.filePath, fileName = mode.fileName),
                consoleLogger = ConsoleLogger,
            )
        }
    }
}
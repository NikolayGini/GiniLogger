package com.example.gini_logger

internal class ConsoleAndFileLogger(
    private val fileLogger: FileLogger,
    private val consoleLogger: ConsoleLogger,
) : Logger {

    override fun log(level: Level, tag: String, message: String) {
        fileLogger.log(level = level, tag = tag, message = message)
        consoleLogger.log(level = level, tag = tag, message = message)
    }
}
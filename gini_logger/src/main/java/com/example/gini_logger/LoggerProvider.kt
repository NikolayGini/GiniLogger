package com.example.gini_logger

fun interface LoggerProvider<T : WritingMode> {

    fun provide(mode: T): Logger

    object Default : LoggerProvider<WritingMode.Default> {

        override fun provide(mode: WritingMode.Default): Logger = when (mode) {
            is WritingMode.Default.File -> {
                FileLogger(filePath = mode.filePath, fileName = mode.fileName)
            }

            WritingMode.Default.Console -> ConsoleLogger

            is WritingMode.Default.ConsoleAndFile -> {
                ConsoleAndFileLogger(
                    fileLogger = FileLogger(filePath = mode.filePath, fileName = mode.fileName),
                    consoleLogger = ConsoleLogger,
                )
            }
        }
    }
}
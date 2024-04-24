package com.example.gini_logger

fun interface LoggerProvider<T: WritingMode> {

    fun provide(mode: T): Logger

    object Default : LoggerProvider<WritingMode.Default> {

        override fun provide(mode: WritingMode.Default): Logger = when (mode) {
            is WritingMode.Default.File -> {
                FileLogger.getInstance(filePath = mode.filePath, fileName = mode.fileName)
            }
            WritingMode.Default.Console -> ConsoleLogger()
        }
    }
}
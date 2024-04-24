package com.example.gini_logger

internal class LogManager<T : WritingMode>(
    private val initializer: Initializer<T>
) {

    private val writingMode: T get() = initializer.writingMode
    private val loggerProvider: LoggerProvider<T> get() = initializer.loggerProvider
    private val formatter: Formatter get() = initializer.formatter
    private val tagger: Tagger get() = initializer.tagger
    private val logBuilder: LogBuilder get() = initializer.logBuilder

    private val logger: Logger get() = loggerProvider.provide(mode = writingMode)

    fun log(level: Level, message: String) {
        logger.log(
            level = level,
            tag = tagger.tag(),
            message = formatter.format(message)
        )
    }

    fun log(level: Level, block: LogBuilder.() -> Unit) {

        block(logBuilder)

        logger.log(
            level = level,
            tag = tagger.tag(),
            message = formatter.format(logBuilder.build())
        )
    }
}
package com.example.gini_logger

internal class LogManager<out W : WritingMode, out B : LogBuilder>(
    val minLevel: Level,
    writingMode: W,
    loggerProvider: LoggerProvider<W>,
    private val formatter: Formatter,
    private val tagger: Tagger,
    private val logBuilderProvider: LogBuilderProvider<B>,
) {

    private val logger: Logger = loggerProvider.provide(mode = writingMode)

    fun log(level: Level, message: String) {
        level.performByMinLevel {
            logger.log(
                level = level,
                tag = tagger.tag(),
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
                tag = tagger.tag(),
                message = formatter.format(logBuilder.build())
            )
        }
    }

    private inline fun Level.performByMinLevel(block: () -> Unit) {
        if (ordinal >= minLevel.ordinal) block()
    }
}
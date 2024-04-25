package com.example.gini_logger

object GiniLogger {

    private var logManager: LogManager<WritingMode, LogBuilder>? = null

    private val nonNullableLogManager: LogManager<WritingMode, LogBuilder>
        get() = logManager ?: throw RuntimeException(
            "GiniLogger has not been initialized. Must call initialize() or initializeDefault() functions"
        )

    internal val minLevel: Level get() = nonNullableLogManager.minLevel

    fun <W : WritingMode, B : LogBuilder> initialize(
        minLevel: Level,
        writingMode: W,
        loggerProvider: LoggerProvider<W>,
        formatter: Formatter,
        tagger: Tagger,
        logBuilderProvider: LogBuilderProvider<B>,
    ) {
        val initializedLogManager = LogManager(
            minLevel = minLevel,
            writingMode = writingMode,
            loggerProvider = loggerProvider,
            formatter = formatter,
            tagger = tagger,
            logBuilderProvider = logBuilderProvider,
        )

        logManager = initializedLogManager
    }

    fun initializeDefault(
        minLevel: Level = Level.Verbose,
        writingMode: WritingMode.Default = WritingMode.Default.Console,
        loggerProvider: LoggerProvider<WritingMode.Default> = LoggerProvider.Default,
        formatter: Formatter = Formatter.Default,
        tagger: Tagger = Tagger.Default,
        logBuilderProvider: LogBuilderProvider<LogBuilder> = LogBuilderProvider.Default()
    ) {
        val initializedLogManager = LogManager(
            minLevel = minLevel,
            writingMode = writingMode,
            loggerProvider = loggerProvider,
            formatter = formatter,
            tagger = tagger,
            logBuilderProvider = logBuilderProvider,
        )

        logManager = initializedLogManager
    }

    internal fun log(level: Level, message: String) {
        nonNullableLogManager.log(level = level, message = message)
    }

    internal fun log(level: Level, block: LogBuilder.() -> Unit) {
        nonNullableLogManager.log(level = level, block = block)
    }
}

package com.example.gini_logger

object GiniLogger {

    private var logManager: LogManager<out WritingMode>? = null

    private val nonNullableLogManager: LogManager<out WritingMode> get() {
        return logManager ?: throw RuntimeException(
            "GiniLogger has not been initialized. Must call initialize() or initializeDefault() functions"
        )
    }

    fun <T : WritingMode> initialize(
        writingMode: T,
        loggerProvider: LoggerProvider<T>,
        formatter: Formatter,
        tagger: Tagger,
        logBuilder: LogBuilder,
    ) {
        val initializer = Initializer(
            writingMode = writingMode,
            loggerProvider = loggerProvider,
            formatter = formatter,
            tagger = tagger,
            logBuilder = logBuilder,
        )

        logManager = LogManager(initializer = initializer)
    }

    fun initializeDefault(block: Initializer<WritingMode.Default>.() -> Unit = {}) {
        val initializer = Initializer(
            writingMode = WritingMode.Default.Console,
            loggerProvider = LoggerProvider.Default,
            formatter = Formatter.Default,
            tagger = Tagger.Default,
            logBuilder = LogBuilder.Default()
        )

        block(initializer)
        logManager = LogManager(initializer = initializer)
    }

    internal fun log(level: Level, message: String) {
        nonNullableLogManager.log(level = level, message = message)
    }

    internal fun log(level: Level, block: LogBuilder.() -> Unit) {
        nonNullableLogManager.log(level = level, block = block)
    }
}

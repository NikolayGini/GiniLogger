package com.example.gini_logger

class Initializer <T: WritingMode> internal constructor(
    var writingMode: T,
    var loggerProvider: LoggerProvider<T>,
    var formatter: Formatter,
    var tagger: Tagger,
    var logBuilder: LogBuilder
)
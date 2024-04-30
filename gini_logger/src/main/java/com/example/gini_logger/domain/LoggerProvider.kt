package com.example.gini_logger.domain

fun interface LoggerProvider<T : WritingMode> {

    fun provide(mode: T): Logger
}
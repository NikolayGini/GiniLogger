package com.example.gini_logger

fun interface LogBuilderProvider<out T : LogBuilder> {

    fun provide(): T

    class Default : LogBuilderProvider<LogBuilder.Default> {

        override fun provide(): LogBuilder.Default = LogBuilder.Default()
    }
}
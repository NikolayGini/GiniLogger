package com.example.gini_logger.domain

fun interface LogBuilderProvider<out T : LogBuilder> {

    fun provide(): T
}
package com.example.gini_logger

enum class Level { Verbose, Debug, Info, Warn, Error }

fun interface Logger {

    fun log(level: Level, tag: String, message: String)
}
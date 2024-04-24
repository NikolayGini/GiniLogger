package com.example.gini_logger

import android.util.Log

internal class ConsoleLogger : Logger {

    override fun log(
        level: Level,
        tag: String,
        message: String,
    ) {
        Log.println(getPriority(level = level), tag, message)
    }

    private fun getPriority(level: Level): Int = when (level) {
        Level.Verbose -> Log.VERBOSE
        Level.Debug -> Log.DEBUG
        Level.Info -> Log.INFO
        Level.Warn -> Log.WARN
        Level.Error -> Log.ERROR
    }
}
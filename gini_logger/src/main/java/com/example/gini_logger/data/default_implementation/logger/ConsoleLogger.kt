package com.example.gini_logger.data.default_implementation.logger

import android.util.Log
import com.example.gini_logger.domain.Logger
import com.example.gini_logger.domain.model.Level

internal object ConsoleLogger : Logger {

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
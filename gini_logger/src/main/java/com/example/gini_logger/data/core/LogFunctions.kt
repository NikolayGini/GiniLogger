package com.example.gini_logger.data.core

import com.example.gini_logger.domain.LogBuilder
import com.example.gini_logger.domain.model.Level

/**
 * Logs the provided message at the specified level
 *
 * @param message string to log
 * @param level to log
 * */

fun log(message: String, level: Level = GiniLogger.minLevel) {
    GiniLogger.log(level = level, message = message)
}
fun logV(message: Any) {
    GiniLogger.log(level = Level.Verbose, message = message.toString())
}

fun logD(message: Any) {
    GiniLogger.log(level = Level.Debug, message = message.toString())
}

fun logI(message: Any) {
    GiniLogger.log(level = Level.Info, message = message.toString())
}

fun logW(message: Any) {
    GiniLogger.log(level = Level.Warn, message = message.toString())
}

fun logE(message: Any) {
    GiniLogger.log(level = Level.Error, message = message.toString())
}

fun log(level: Level = GiniLogger.minLevel, block: LogBuilder.() -> Unit) {
    GiniLogger.log(level = level, block = block)
}

fun logV(block: LogBuilder.() -> Unit) {
    GiniLogger.log(level = Level.Verbose, block = block)
}

fun logD(block: LogBuilder.() -> Unit) {
    GiniLogger.log(level = Level.Debug, block = block)
}

fun logI(block: LogBuilder.() -> Unit) {
    GiniLogger.log(level = Level.Info, block = block)
}

fun logW(block: LogBuilder.() -> Unit) {
    GiniLogger.log(level = Level.Warn, block = block)
}

fun logE(block: LogBuilder.() -> Unit) {
    GiniLogger.log(level = Level.Error, block = block)
}
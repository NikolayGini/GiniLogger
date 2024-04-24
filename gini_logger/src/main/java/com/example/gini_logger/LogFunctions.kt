package com.example.gini_logger

fun logD(message: Any) {
    GiniLogger.log(level = Level.Debug, message = message.toString())
}

fun logI(message: Any) {
    GiniLogger.log(level = Level.Info, message = message.toString())
}

fun logE(message: Any) {
    GiniLogger.log(level = Level.Error, message = message.toString())
}

fun logD(block: LogBuilder.() -> Unit) {
    GiniLogger.log(level = Level.Debug, block = block)
}

fun logI(block: LogBuilder.() -> Unit) {
    GiniLogger.log(level = Level.Info, block = block)
}

fun logE(block: LogBuilder.() -> Unit) {
    GiniLogger.log(level = Level.Error, block = block)
}
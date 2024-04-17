package com.example.gini_logger

fun logD(message: Any) {
    log(level = Level.Debug, message = message)
}

fun logI(message: Any) {
    log(level = Level.Info, message = message)
}

fun logE(message: Any) {
    log(level = Level.Error, message = message)
}

fun logD(block: Builder.() -> Unit) {
    log(level = Level.Debug, block = block)
}

fun logI(block: Builder.() -> Unit) {
    log(level = Level.Info, block = block)
}

fun logE(block: Builder.() -> Unit) {
    log(level = Level.Error, block = block)
}
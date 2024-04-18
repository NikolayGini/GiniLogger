package com.example.gini_logger

import android.util.Log

internal enum class Level { Debug, Info, Error }

internal fun log(level: Level, message: Any, pointer: String = "---> ") {
    val tag = getClassName()
    val messageWithPointer = "$pointer$message"

    Log.println(getPriority(level = level), tag, messageWithPointer)
}

internal fun getPriority(level: Level): Int = when (level) {
    Level.Debug -> Log.DEBUG
    Level.Info -> Log.INFO
    Level.Error -> Log.ERROR
}

internal fun log(level: Level, block: Builder.() -> Unit) {
    val builtMessage = Builder().run {
        block()
        build()
    }

    log(level = level, message = builtMessage)
}

internal fun getClassName(): String = Throwable().stackTrace[5]
    ?.className
    ?.replace("\\$\\d".toRegex(), "")
    ?.substringAfterLast('.')
    ?: "UnknownClass"
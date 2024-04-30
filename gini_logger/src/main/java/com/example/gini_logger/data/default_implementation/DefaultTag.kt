package com.example.gini_logger.data.default_implementation

object DefaultTag {

    val value: String
        get() = Throwable().stackTrace[5]
            ?.className
            ?.replace("\\$\\d".toRegex(), "")
            ?.substringAfterLast('.')
            ?: "UnknownClass"
}
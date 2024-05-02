package com.example.gini_logger.data.default_implementation

/**
 * Provides a default tag for logging purposes by extracting the class name from the call stack.
 *
 * This object is typically used to automatically generate a tag based on the class
 * from which the logging method is called. It helps in identifying the source of log messages
 * when a specific tag is not manually provided.
 */

object DefaultTag {

    /**
     * Retrieves the class name from the call stack as a logging tag.
     *
     * This property dynamically computes the tag by inspecting the stack trace to find the
     * caller's class name. It simplifies the tag assignment in logging operations by providing
     * a meaningful default based on the caller's context.
     *
     * @return The class name of the logger's caller or "UnknownClass" if it cannot be resolved.
     */

    val value: String
        get() = Throwable().stackTrace[5]
            ?.className
            ?.replace("\\$\\d".toRegex(), "")
            ?.substringAfterLast('.')
            ?: "UnknownClass"
}
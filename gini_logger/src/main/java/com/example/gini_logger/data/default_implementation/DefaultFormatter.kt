package com.example.gini_logger.data.default_implementation

import com.example.gini_logger.domain.Formatter

object DefaultFormatter : Formatter {

    private const val POINTER = "--->"

    override fun format(message: String): String {
        return "$POINTER $message"
    }
}
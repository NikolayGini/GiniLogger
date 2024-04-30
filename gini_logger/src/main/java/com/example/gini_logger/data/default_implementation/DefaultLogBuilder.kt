package com.example.gini_logger.data.default_implementation

import com.example.gini_logger.domain.LogBuilder

class DefaultLogBuilder : LogBuilder {

    private val titleBuilder = StringBuilder()
    private val contentBuilder = StringBuilder()

    override fun message(value: Any) {
        if (titleBuilder.isEmpty()) {
            titleBuilder.append(value)
        } else {
            contentBuilder.append("\n\t-> $value")
        }
    }

    override fun build(): String = titleBuilder.toString() + contentBuilder.toString()
}
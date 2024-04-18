package com.example.gini_logger

class Builder {

    private val titleBuilder = StringBuilder()
    private val contentBuilder = StringBuilder()

    fun title(value: Any) {
        titleBuilder.append(value)
    }

    fun message(value: Any) {
        contentBuilder.append("\n\t-> $value")
    }

    fun build(): String = titleBuilder.toString() + contentBuilder.toString()
}
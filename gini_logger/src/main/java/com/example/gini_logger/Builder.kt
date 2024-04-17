package com.example.gini_logger

class Builder {

    private val stringBuilder = StringBuilder()

    fun message(value: Any) {
        val prefix = if (stringBuilder.isEmpty()) "---> " else "\n\t-> "

        stringBuilder.append("$prefix $value")
    }

    fun build(): String = stringBuilder.toString()
}
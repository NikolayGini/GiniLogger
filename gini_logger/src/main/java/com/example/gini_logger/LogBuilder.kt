package com.example.gini_logger

interface LogBuilder {

    fun build(): String

    fun message(value: Any)

    class Default : LogBuilder {

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
}
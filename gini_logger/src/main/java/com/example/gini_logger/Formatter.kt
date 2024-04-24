package com.example.gini_logger

fun interface Formatter {

    fun format(message: String): String

    object Default : Formatter {

        private const val POINTER = "--->"

        override fun format(message: String): String {
            return "$POINTER $message"
        }
    }
}
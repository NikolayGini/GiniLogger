package com.example.gini_logger.domain

fun interface Formatter {

    fun format(message: String): String
}
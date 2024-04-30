package com.example.gini_logger.domain

interface LogBuilder {

    fun build(): String

    fun message(value: Any)
}
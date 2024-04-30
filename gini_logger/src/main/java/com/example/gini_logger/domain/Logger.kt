package com.example.gini_logger.domain

import com.example.gini_logger.domain.model.Level

fun interface Logger {

    fun log(level: Level, tag: String, message: String)
}
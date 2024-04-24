package com.example.gini_logger

fun interface Tagger {

    fun tag(): String

    object Default : Tagger {

        override fun tag(): String = Throwable().stackTrace[5]
            ?.className
            ?.replace("\\$\\d".toRegex(), "")
            ?.substringAfterLast('.')
            ?: "UnknownClass"
    }
}
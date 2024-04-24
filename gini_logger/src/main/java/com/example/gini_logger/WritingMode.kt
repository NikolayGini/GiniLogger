package com.example.gini_logger

interface WritingMode {

    sealed interface Default : WritingMode {

        data class File(
            val filePath: String,
            val fileName: String = FileLogger.DEFAULT_FILE_NAME
        ) : Default

        data object Console : Default
    }
}
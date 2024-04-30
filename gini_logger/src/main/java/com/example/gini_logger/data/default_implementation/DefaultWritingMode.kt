package com.example.gini_logger.data.default_implementation

import com.example.gini_logger.data.default_implementation.logger.FileLogger
import com.example.gini_logger.domain.WritingMode

sealed interface DefaultWritingMode : WritingMode {

    data class File(
        val filePath: String,
        val fileName: String = FileLogger.DEFAULT_FILE_NAME
    ) : DefaultWritingMode

    data object Console : DefaultWritingMode

    data class ConsoleAndFile(
        val filePath: String,
        val fileName: String = FileLogger.DEFAULT_FILE_NAME
    ) : DefaultWritingMode
}
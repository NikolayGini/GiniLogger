package com.example.gini_logger

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal class FileLogger private constructor(filePath: String, fileName: String) : Logger {

    companion object {

        private const val FILE_NAME_DATE_PATTERN = "dd.MM.yyyy_HH:mm:ss"
        private const val LOG_DATE_PATTERN = "dd-MM-yyyy HH:mm:ss.SSS"
        internal const val DEFAULT_FILE_NAME = "gini_logger"

        private var instance: FileLogger? = null
        private val LOCK = Any()

        internal fun getInstance(filePath: String, fileName: String): FileLogger = synchronized(LOCK) {
            instance ?: FileLogger(filePath = filePath, fileName = fileName)
                .also { instance = it }
        }
    }

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val mutex: Mutex = Mutex()
    private val file = File(buildFileName(filePath = filePath, fileName = fileName))

    override fun log(
        level: Level,
        tag: String,
        message: String,
    ) {
        logToFile(level = level, tag = tag, message = message)
    }

    private fun logToFile(level: Level, tag: String, message: String) {
        scope.launch {
            createFileIfNeeded(file = file)

            try {
                val date = formatDate(
                    timestamp = System.currentTimeMillis(),
                    pattern = LOG_DATE_PATTERN
                )
                val fullMessage = "$date $level $tag $message"
                val bufferWriter = BufferedWriter(FileWriter(file, true))

                mutex.withLock {
                    bufferWriter.use { buf ->
                        buf.append(fullMessage)
                        buf.newLine()
                        buf.close()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun buildFileName(filePath: String, fileName: String): String {
        val date = formatDate(timestamp = System.currentTimeMillis())
        return "$filePath/${fileName}_$date.txt"
    }

    private fun formatDate(timestamp: Long, pattern: String = FILE_NAME_DATE_PATTERN): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(Date(timestamp))
    }

    private fun createFileIfNeeded(file: File) {
        if (file.exists()) return

        try {
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
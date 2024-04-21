package com.example.gini_logger

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
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

object FileLogger {

    private const val FILE_NAME_DATE_PATTERN = "dd.MM.yyyy_HH:mm:ss"
    private const val LOG_DATE_PATTERN = "dd-MM-yyyy HH:mm:ss.SSS"

    private var writingToFileEnabled = false
    private var logsFile: File? = null
    private var scope: CoroutineScope? = null
    private var mutex: Mutex? = null

    fun enableToWriteLogsToFile(filePath: String, fileName: String) {
        writingToFileEnabled = true
        scope = createCoroutineScope()
        mutex = Mutex()

        val fullFilePath = buildFileName(filePath = filePath, fileName = fileName)

        logsFile = File(fullFilePath)
    }

    fun disableToWriteLogsTofile() {
        writingToFileEnabled = false
        scope?.cancel()
        logsFile = null
        scope = null
        mutex = null
    }

    internal fun logToFile(message: String, level: Level) {
        if (!writingToFileEnabled) return

        val logFile = logsFile ?: throw RuntimeException("File is not assigned")

        scope?.launch {
            createFileIfNeeded(file = logFile)

            try {
                val date = formatDate(
                    timestamp = System.currentTimeMillis(),
                    pattern = LOG_DATE_PATTERN
                )
                val fullMessage = "$date $level $message"
                val bufferWriter = BufferedWriter(FileWriter(logFile, true))

                mutex?.withLock {
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

    private fun createCoroutineScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO + SupervisorJob())
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
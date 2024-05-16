package com.example.gini_logger.data.default_implementation

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Formats a given timestamp into a string based on the specified date pattern.
 *
 * @param timestamp The timestamp in milliseconds to be formatted.
 * @param pattern The pattern to be used for formatting the date. Defaults to [LOG_DATE_PATTERN].
 * @return A formatted date string.
 *
 * @throws IllegalArgumentException If the given pattern is invalid.
 */

private const val LOG_DATE_PATTERN = "dd-MM-yyyy HH:mm:ss.SSS"

internal fun formatDate(timestamp: Long, pattern: String = LOG_DATE_PATTERN): String {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(Date(timestamp))
}
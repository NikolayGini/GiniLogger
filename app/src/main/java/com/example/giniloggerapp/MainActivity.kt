package com.example.giniloggerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.gini_logger.GiniLogger
import com.example.gini_logger.Level
import com.example.gini_logger.LogBuilder
import com.example.gini_logger.LogBuilderProvider
import com.example.gini_logger.Logger
import com.example.gini_logger.WritingMode
import com.example.gini_logger.logD
import com.example.gini_logger.logE
import com.example.gini_logger.logI
import com.example.giniloggerapp.ui.theme.GiniLoggerAppTheme
import timber.log.Timber

enum class CustomWritingMode : WritingMode { Local, Remote }

class MainActivity : ComponentActivity() {

    private val data = listOf(
        "first",
        "second",
        "third",
        "forth",
        "fifth",
        "sixth",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        GiniLogger.initializeDefault(
            /** uncomment this to check logging to file */
//            writingMode = WritingMode.Default.File(filePath = filesDir.path)
            /** uncomment this to check logging to file and console */
//            writingMode = WritingMode.Default.ConsoleAndFile(filePath = filesDir.path)
        )

        /** customising default implementation */
//        GiniLogger.initializeDefault(
//            minLevel = Level.Debug,
//            writingMode = WritingMode.Default.Console,
//            formatter = { message -> "return formatted message: $message" },
//            tagger = { "custom tag logic" },
//            loggerProvider = { mode: WritingMode.Default ->
//                when (mode) {
//                    is WritingMode.Default.File -> {
//                        Logger { level: Level, tag: String, message: String -> /** your logic */ }
//                    }
//
//                    WritingMode.Default.Console -> {
//                        Logger { level: Level, tag: String, message: String -> /** your logic */ }
//                    }
//
//                    is WritingMode.Default.ConsoleAndFile -> {
//                        Logger { level: Level, tag: String, message: String -> /** your logic */ }
//                    }
//                }
//            },
//            logBuilderProvider = {
//                /** provide your custom LogBuilder */
//                object : LogBuilder {
//
//                    override fun build(): String {
//                        return "build your string"
//                    }
//
//                    override fun message(value: Any) {
//                        /** use this block to build multiple log */
//                    }
//                }
//            }
//        )

        /** custom implementation */
//        GiniLogger.initialize(
//            minLevel = Level.Debug,
//            writingMode = CustomWritingMode.Remote,
//            formatter = { message -> "return formatted message: $message" },
//            tagger = { "custom tag logic" },
//            loggerProvider = { mode: CustomWritingMode ->
//                when (mode) {
//                    CustomWritingMode.Local -> {
//                        Logger { level: Level, tag: String, message: String -> /** your logic */ }
//                    }
//
//                    CustomWritingMode.Remote -> {
//                        Logger { level: Level, tag: String, message: String -> /** your logic */ }
//                    }
//                }
//            },
//            logBuilderProvider = {
//
//                /** provide your custom LogBuilder */
//                object : LogBuilder {
//
//                    override fun build(): String {
//                        return "build your string"
//                    }
//
//                    override fun message(value: Any) {
//                        /** use this block to build multiple log */
//                    }
//                }
//            }
//        )

        setContent {
            GiniLoggerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    MainScreen(
//                        data = data,
//                        onDebugClick = { logD(message = it) },
//                        onInfoClick = { logI(message = it) },
//                        onErrorClick = { logE(message = it) },
//                        onMultipleLogClick = { invokeMultipleLog() },
//                    )
                }
            }
        }
    }

    private fun invokeMultipleLog() {
        logD {
            message("Multiple log")
            data.forEach { message(value = it) }
        }
    }
}
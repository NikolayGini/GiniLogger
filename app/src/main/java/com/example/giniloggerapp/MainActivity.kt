package com.example.giniloggerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.gini_logger.core.GiniLogger
import com.example.gini_logger.core.log
import com.example.gini_logger.core.logD
import com.example.gini_logger.core.logE
import com.example.gini_logger.core.logI
import com.example.gini_logger.core.logV
import com.example.gini_logger.core.logW
import com.example.gini_logger.data.default_implementation.logger.ConsoleAndFileLogger
import com.example.gini_logger.data.default_implementation.logger.FileLogger
import com.example.gini_logger.data.default_implementation.logger.RemoteLogger
import com.example.gini_logger.domain.LogBuilder
import com.example.gini_logger.domain.Logger
import com.example.gini_logger.domain.model.Level
import com.example.giniloggerapp.ui.theme.GiniLoggerAppTheme
import com.example.giniloogertestapp.MainScreen
import timber.log.Timber


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

        GiniLogger.initialize(
            /** uncomment this to check logging to file */
//            logger = FileLogger(filePath = filesDir.path)
            /** uncomment this to check logging to file and console */
//            logger = ConsoleAndFileLogger(fileLogger = FileLogger(filePath = filesDir.path))
            /** uncomment this to check logging to remote */
            logger = RemoteLogger.getInstance(url = "https://echo.free.beeceptor.com")
        )

        /** custom implementation */
//        GiniLogger.initialize(
//            minLevel = Level.Debug,
//            logger = { level: Level, tag: String, message: String -> /** your logic */ },
//            formatter = { message -> "return formatted message: $message" },
//            tag = "custom tag",
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

        setContent {
            GiniLoggerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MainScreen(
                        data = data,
                        onVerboseClick = { logV(message = it) },
                        onDebugClick = { logD(message = it) },
                        onInfoClick = { logI(message = it) },
                        onWarnClick = { logW(message = it) },
                        onErrorClick = { logE(message = it) },
                        onMultipleVerboseLogClick = { invokeMultipleLog(level = Level.Verbose) },
                        onMultipleDebugLogClick = { invokeMultipleLog(level = Level.Debug) },
                        onMultipleInfoLogClick = { invokeMultipleLog(level = Level.Info) },
                        onMultipleWarnLogClick = { invokeMultipleLog(level = Level.Warn) },
                        onMultipleErrorLogClick = { invokeMultipleLog(level = Level.Error) },
                    )
                }
            }
        }
    }

    private fun invokeMultipleLog(level: Level) {
        log(level = level) {
            message("Multiple log")
            data.forEach { message(value = it) }
        }
    }
}
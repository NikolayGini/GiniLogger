package com.example.giniloggerapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gini_logger.logD
import com.example.gini_logger.logE
import com.example.gini_logger.logI
import com.example.giniloggerapp.ui.theme.GiniLoggerAppTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        logI(message = 1314324)

        Intent().apply {

            logE(message = "gini logger")

            logI {
                message("logging via builder")
                message("first")
                message("second")
                message("third")
            }

            logD { message("debug level log") }
        }

        setContent {
            GiniLoggerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    logE(message = "from greeting")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GiniLoggerAppTheme {
        Greeting("Android")
    }
}
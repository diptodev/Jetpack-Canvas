package com.excitedbroltd.jetpackcanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.excitedbroltd.jetpackcanvas.ui.theme.JetpackCanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCanvasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Blue
                ) {
                    waveCanvas()
                }
            }
        }


    }

    @Composable
    fun waveCanvas() {

    }

    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    fun previewWaveCanvas() {
        waveCanvas()
    }

}
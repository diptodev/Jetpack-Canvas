package com.excitedbroltd.jetpackcanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.excitedbroltd.jetpackcanvas.ui.theme.JetpackCanvasTheme
import com.excitedbroltd.mylibrary.MyProgressBar
import com.excitedbroltd.mylibrary.MyProgressBar.Companion.waveCanvas

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCanvasTheme {
                // A surface container using the 'background' color from the theme
                var text by remember {
                    mutableStateOf("100GB")
                }
                var progressValue by remember {
                    mutableStateOf(0f)
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    waveCanvas(
                        text = text,
                        progressValue = progressValue,
                        maximumProgress = 1000f,
                        animationDuration = 1000
                    )
                    TextField(
                        value = "$progressValue",
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            progressValue = it.toFloat()
                        })
                    Button(onClick = { }) {
                        Text(text = "Update")
                    }
                }

            }
        }


    }


    @Composable
    @Preview(showBackground = true)
    fun previewWaveCanvas() {
        waveCanvas()
    }

}
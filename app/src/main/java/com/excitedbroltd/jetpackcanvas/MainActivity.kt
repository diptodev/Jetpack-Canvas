package com.excitedbroltd.jetpackcanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.excitedbroltd.jetpackcanvas.ui.theme.JetpackCanvasTheme

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
                        maximumProgress = 80f,
                        animationDuration = 500
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
        waveCanvas(text = "100 GB")
    }


    @Composable
    private fun waveCanvas(
        startAngle: Float = 150f,
        sweepAngle: Float = 240f,
        progressValue: Float = 0f,
        progressbarWidth: Int = 15,
        maximumProgress: Float = 100f,
        strokeCap: StrokeCap = StrokeCap.Round,
        secondaryColor: Color = Color.LightGray.copy(alpha = 0.5f),
        primaryColor: Color = Color.Blue.copy(alpha = 0.8f),
        modifier: Modifier = Modifier
            .padding(10.dp)
            .size(300.dp),
        useCenter: Boolean = false,
        text: String = "0 GB",
        textModifier: Modifier = Modifier.padding(10.dp),
        animationDuration: Int = 1000
    ) {
        var existingValue by remember {
            mutableStateOf(0f)
        }
        if (progressValue < maximumProgress) existingValue = progressValue
        var progressValue = if (progressValue < maximumProgress) progressValue else existingValue
        val animate by animateFloatAsState(
            targetValue = (progressValue / 100) * 240 * (100 / maximumProgress),
            animationSpec = tween(animationDuration)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .drawBehind {
                    drawArc(
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = useCenter,
                        color = secondaryColor,
                        style = Stroke(width = progressbarWidth.dp.toPx(), cap = strokeCap),
                    )
                    drawArc(
                        startAngle = startAngle,
                        sweepAngle = animate,
                        useCenter = useCenter,
                        color = primaryColor,
                        style = Stroke(width = progressbarWidth.dp.toPx(), cap = strokeCap),
                    )

                }) {
            Text(text = text, modifier = textModifier)
        }

    }

}
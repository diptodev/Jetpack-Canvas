package com.excitedbroltd.mylibrary

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp

class MyProgressBar {
    companion object {

        @Composable
          fun waveCanvas(
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
            if (progressValue <= maximumProgress) existingValue = progressValue
            var progressValue = if (progressValue <= maximumProgress) progressValue else existingValue
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

}
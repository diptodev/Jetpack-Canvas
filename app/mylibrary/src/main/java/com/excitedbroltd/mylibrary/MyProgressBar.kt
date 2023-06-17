package com.excitedbroltd.mylibrary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            startAngleSecondary: Float = 150f,
            sweepAngleSecondary: Float = 240f,
            startAnglePrimary: Float = 150f,
            sweepAnglePrimary: Float = 0f,
            progressbarWidth: Int = 15,
            strokeCap: StrokeCap = StrokeCap.Round,
            secondaryColor: Color = Color.LightGray.copy(alpha = 0.5f),
            primaryColor: Color = Color.Blue.copy(alpha = 0.8f),
            modifier: Modifier = Modifier
                .padding(10.dp)
                .size(300.dp),
            useCenter: Boolean = false

        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .drawBehind {
                        drawArc(
                            startAngle = startAngleSecondary,
                            sweepAngle = sweepAngleSecondary,
                            useCenter = useCenter,
                            color = secondaryColor,
                            style = Stroke(width = progressbarWidth.dp.toPx(), cap = strokeCap),
                        )
                        drawArc(
                            startAngle = startAnglePrimary,
                            sweepAngle = sweepAnglePrimary,
                            useCenter = useCenter,
                            color = primaryColor,
                            style = Stroke(width = progressbarWidth.dp.toPx(), cap = strokeCap),
                        )

                    }) {
                Text(text = "10GB")
            }

        }
    }

}
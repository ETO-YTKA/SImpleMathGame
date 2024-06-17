package com.example.mathapp.features.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mathapp.R
import com.example.mathapp.ui.theme.MathAppTheme

@Composable
fun StatsProgressBar(
    rightAnswers: Int,
    wrongAnswers: Int,
    modifier: Modifier = Modifier
) {
    val totalAnswers = rightAnswers + wrongAnswers
    val progressValue = if (totalAnswers > 0) rightAnswers / totalAnswers.toFloat() else 0f

    var progress by remember { mutableFloatStateOf(progressValue) }
    progress = progressValue

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 500)
    )

    val context = LocalContext.current
    val trackColor = Color(
        context.getColor(
            if(totalAnswers == 0) R.color.progress_indicator_White
            else R.color.progress_indicator_Red
        )
    )
    val color = Color(context.getColor(R.color.progress_indicator_Green))

    LinearProgressIndicator(
        progress = animatedProgress,
        trackColor = trackColor,
        color = color,
        modifier = modifier
            .height(20.dp)
            .width(200.dp)
            .clip(shape = RoundedCornerShape(10.dp))
    )
}

@Preview
@Composable
fun StatsProgressBarPreview() {
    MathAppTheme {
        StatsProgressBar(rightAnswers = 1, wrongAnswers = 1)
    }
}


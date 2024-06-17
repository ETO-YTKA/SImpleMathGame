package com.example.mathapp.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mathapp.Game
import com.example.mathapp.R

@Composable
fun GameStatsPanel(game: Game, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(40.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = stringResource( R.string.total_questions),
            style = MaterialTheme.typography.bodyMedium
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${game.rightAnswers}",
                style = MaterialTheme.typography.bodyMedium
            )
            StatsProgressBar(
                rightAnswers = game.rightAnswers,
                wrongAnswers = game.wrongAnswers,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = "${game.wrongAnswers}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        val totalQuestions = game.rightAnswers + game.wrongAnswers
        Text(
            text = totalQuestions.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun GameStatsPanelPreview() {
    GameStatsPanel(game = Game(4, 2))
}
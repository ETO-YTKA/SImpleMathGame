package com.example.mathapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mathapp.features.component.ExpressionAndTextField
import com.example.mathapp.features.component.GameStatsPanel
import com.example.mathapp.features.component.SimpleButton
import com.example.mathapp.ui.theme.MathAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MathApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MathApp(modifier: Modifier = Modifier) {
    val game = remember { mutableStateOf(Game()) }
    var userAnswer by remember { mutableStateOf("") }
    val expression by game.value.expression.collectAsState()

    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        GameStatsPanel(
            game = game.value,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(40.dp)
                )
                .padding(10.dp)
        ) {
            ExpressionAndTextField(
                expression = expression,
                userAnswer = userAnswer,
                onValueChange = { userAnswer = it },
                onDone = {
                    game.value.checkAnswer(userAnswer)
                    userAnswer = ""
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                //checkButton
                SimpleButton(
                    text = stringResource(R.string.check),
                    onClick = {
                        game.value.checkAnswer(userAnswer)
                        userAnswer = ""
                    },
                    enabled = game.value.isStarted
                )
                //startButton
                SimpleButton(
                    text = stringResource(R.string.start),
                    onClick = {
                        game.value.generateExpression()
                        game.value.isStarted = true
                        userAnswer = ""
                    },
                    enabled = !game.value.isStarted
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MathAppPreview() {
    MathAppTheme {
        MathApp()
    }
}

package com.example.mathapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = modifier.padding(16.dp)
    ) {
        GameStatsPanel(
            game = game.value,
            modifier = Modifier.fillMaxWidth()
        )
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

@Composable
fun GameStatsPanel(game: Game, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(8.dp)
    ) {
        GameStat(
            statName = stringResource(R.string.right_answer),
            statValue = game.rightAnswers,
            modifier = Modifier.weight(1f)
        )
        GameStat(
            statName = stringResource(R.string.wrong_answer),
            statValue = game.wrongAnswers,
            modifier = Modifier.weight(1f)
        )
        GameStat(
            statName = stringResource(R.string.total_questions),
            statValue = game.rightAnswers + game.wrongAnswers,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun GameStat(statName: String, statValue: Int, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(statName, textAlign = TextAlign.Center)
        Text(statValue.toString())
    }
}

@Composable
fun ExpressionAndTextField(
    expression: Expression,
    userAnswer: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "${expression.operand1}",
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)  
                .width(40.dp)
        )
        Text(
            expression.operator,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
                .width(40.dp)
        )
        Text(
            "${expression.operand2}",
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
                .width(40.dp)
        )
        Text(
            "=",
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
                .width(40.dp)
        )
        TextField(
            value = userAnswer,
            singleLine = true,
            onValueChange = onValueChange,
            placeholder = { Text(text = stringResource(R.string.answer)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = { onDone() }),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            ),
            modifier = Modifier.weight(2f)
        )
    }
}

@Composable
fun SimpleButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    Button(
        onClick = onClick,
        enabled = enabled
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MathAppTheme {
        MathApp()
    }
}
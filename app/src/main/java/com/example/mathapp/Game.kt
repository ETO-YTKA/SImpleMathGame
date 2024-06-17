package com.example.mathapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Game(
    var rightAnswers: Int = 0,
    var wrongAnswers: Int = 0,
    private val _expression: MutableStateFlow<Expression> = MutableStateFlow(Expression()),
    var isStarted: Boolean = false
) {
    val expression: StateFlow<Expression> = _expression.asStateFlow()

    fun checkAnswer(userAnswer: String) {
        val currentExpression = _expression.value
        val operand1 = currentExpression.operand1
        val operand2 = currentExpression.operand2
        val operator = currentExpression.operator
        val userAnswerInt = userAnswer.toIntOrNull() ?: -1

        if (userAnswerInt == -1) {
            wrongAnswers++
            _expression.value = Expression().generateExpression()
            return
        }

        val isCorrect = when (operator) {
            "+" -> userAnswerInt == operand1 + operand2
            "-" -> userAnswerInt == operand1 - operand2
            "*" -> userAnswerInt == operand1 * operand2
            ":" -> userAnswerInt == operand1 / operand2
            else -> false
        }

        if (isCorrect) {
            rightAnswers++
        } else {
            wrongAnswers++
        }
        _expression.value = Expression().generateExpression()
    }

    fun generateExpression() { _expression.value = Expression().generateExpression() }
}
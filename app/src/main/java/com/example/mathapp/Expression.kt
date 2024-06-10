package com.example.mathapp

class Expression(
    val operator: String = generateOperator(),
    val operand1: Int = generateOperands(operator)[0],
    val operand2: Int = generateOperands(operator)[1]
) {
    fun generateExpression(): Expression {
        val newOperator = generateOperator()
        val (newOperand1, newOperand2) = generateOperands(newOperator)
        return Expression(newOperator, newOperand1, newOperand2)
    }

    private companion object {
        fun generateOperator(): String = listOf(":", "+", "-", "*").random()

        fun generateOperands(operator: String = "+"): List<Int> {
            return when (operator) {
                ":" -> {
                    val operand2 = (2..10).random()
                    val operand1 = (2..10).random() * operand2
                    listOf(operand1, operand2)
                }
                "-" -> {
                    val operand1 = (2..99).random()
                    val operand2 = (2..operand1).random()
                    listOf(operand1, operand2)
                }
                else -> {
                    val operand1 = (2..99).random()
                    val operand2 = (2..99).random()
                    listOf(operand1, operand2)
                }
            }
        }
    }
}
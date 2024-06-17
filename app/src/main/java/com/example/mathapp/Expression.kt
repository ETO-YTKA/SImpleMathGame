package com.example.mathapp

class Expression(
    val operator: String = "+",
    val operand1: Int = 0,
    val operand2: Int = 0
) {
    fun generateExpression(): Expression {
        val newOperator = generateOperator()
        val (newOperand1, newOperand2) = generateOperands(newOperator)
        return Expression(newOperator, newOperand1, newOperand2)
    }

    override fun toString(): String { return "$operand1 $operator $operand2" }

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
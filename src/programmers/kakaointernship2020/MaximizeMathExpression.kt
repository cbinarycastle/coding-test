package programmers.kakaointernship2020

import java.util.*
import kotlin.math.abs

fun main() {
    println(MaximizeMathExpression().solution("2*2*2*2*2-2*2*2"))
}

class MaximizeMathExpression {

    fun solution(expression: String): Long {
        val expressionUnits = expression.split(Regex("(?<=[+\\-*/])|(?=[+\\-*/])"))
                .toCollection(LinkedList<String>())
        val operatorOrders = permutation(listOf("+", "-", "*"))
        val results = arrayListOf<Long>()

        operatorOrders.forEach { order ->
            val copiedExpressionUnits = expressionUnits.toMutableList()
            order.forEach {
                while (copiedExpressionUnits.contains(it)) {
                    for (i in 1 until copiedExpressionUnits.size - 1 step 2) {
                        val expressionUnit = copiedExpressionUnits[i]
                        if (expressionUnit == it) {
                            val left = copiedExpressionUnits[i - 1].toLong()
                            val right = copiedExpressionUnits[i + 1].toLong()
                            val result = when (expressionUnit) {
                                "+" -> left + right
                                "-" -> left - right
                                "*" -> left * right
                                else -> 0
                            }
                            copiedExpressionUnits[i] = result.toString()
                            copiedExpressionUnits.removeAt(i + 1)
                            copiedExpressionUnits.removeAt(i - 1)
                            break
                        }
                    }
                }
            }
            results.add(copiedExpressionUnits.first().toLong())
        }

        var max = 0L
        for (result in results) {
            val absResult = abs(result)
            if (absResult > max) {
                max = absResult
            }
        }

        return max
    }

    private fun <T> permutation(arr: List<T>, result: List<T> = listOf()): List<List<T>> {
        return if (arr.isEmpty()) {
            listOf(result)
        } else {
            arr.flatMap { permutation(arr - it, result + it) }
        }
    }
}
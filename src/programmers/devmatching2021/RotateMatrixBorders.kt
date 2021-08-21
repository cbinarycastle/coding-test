package programmers.devmatching2021

import kotlin.math.min

fun main() {
    RotateMatrixBorders().solution(6, 6, arrayOf(intArrayOf(2, 2, 5, 4), intArrayOf(3, 3, 6, 6), intArrayOf(5, 1, 6, 3)))
}

class RotateMatrixBorders {

    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        val matrix = Array(rows) { Array(columns) { 0 } }
        for (r in 0 until rows) {
            for (c in 0 until columns) {
                matrix[r][c] = columns * r + c + 1
            }
        }

        val minNums = mutableListOf<Int>()
        queries.forEach {
            val r1 = it[0] - 1
            val c1 = it[1] - 1
            val r2 = it[2] - 1
            val c2 = it[3] - 1

            var currentNum: Int
            var nextNum = matrix[r1][c1]
            var minNum = 10000

            for (c in c1 until c2) {
                currentNum = nextNum
                nextNum = matrix[r1][c + 1]
                matrix[r1][c + 1] = currentNum
                minNum = min(minNum, currentNum)
            }
            for (r in r1 until r2) {
                currentNum = nextNum
                nextNum = matrix[r + 1][c2]
                matrix[r + 1][c2] = currentNum
                minNum = min(minNum, currentNum)
            }
            for (c in c2 downTo c1 + 1) {
                currentNum = nextNum
                nextNum = matrix[r2][c - 1]
                matrix[r2][c - 1] = currentNum
                minNum = min(minNum, currentNum)
            }
            for (r in r2 downTo r1 + 1) {
                currentNum = nextNum
                nextNum = matrix[r - 1][c1]
                matrix[r - 1][c1] = currentNum
                minNum = min(minNum, currentNum)
            }

            minNums.add(minNum)
        }

        return minNums.toIntArray()
    }
}
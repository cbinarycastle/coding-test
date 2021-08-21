package programmers.naverwebtoondevchallenge2021

import kotlin.test.assertEquals

fun main() {
    assertEquals(
            5,
            Problem2().solution(arrayOf(1, 2, 5, 3, 1, 0, 2, 3).toIntArray(), 6, 3)
    )
    assertEquals(
            2,
            Problem2().solution(arrayOf(0, 1, 2, 3, 4).toIntArray(), 5, 2)
    )
    assertEquals(
            1,
            Problem2().solution(arrayOf(0, 1, 2, 3, 4, 3).toIntArray(), 5, 2)
    )
    assertEquals(
            2,
            Problem2().solution(arrayOf(0, 1, 2, 2, 2, 0).toIntArray(), 3, 3)
    )
    assertEquals(
            1,
            Problem2().solution(arrayOf(3, 3, 3, 3, 1, 3).toIntArray(), 4, 2)
    )
    assertEquals(
            0,
            Problem2().solution(arrayOf(5, 5, 0, 5, 0, 5).toIntArray(), 5, 2)
    )
    assertEquals(
            4,
            Problem2().solution(arrayOf(1, 4, 5, 4, 1, 1, 1, 1).toIntArray(), 6, 3)
    )
}

class Problem2 {

//    fun solution(bricks: IntArray, n: Int, k: Int): Int {
//        val bricksExceptFirstAndLast = bricks.filterIndexed { index, _ -> index in 1 until bricks.lastIndex }
//        val visited = BooleanArray(bricksExceptFirstAndLast.size)
//
//        var columns = k - 1
//        var numberOfBricks = 0
//        while (columns > 0) {
//            var maxNum = 0
//            var maxIndex = 0
//            bricksExceptFirstAndLast.forEachIndexed { index, num ->
//                if (num > maxNum && !visited[index] &&
//                        (index == 0 || !visited[index - 1]) &&
//                        (index == bricksExceptFirstAndLast.lastIndex || !visited[index + 1])) {
//                    maxNum = num
//                    maxIndex = index
//                }
//            }
//            visited[maxIndex] = true
//            numberOfBricks += n - maxNum
//            columns--
//        }
//
//        return numberOfBricks
//    }

    fun solution(bricks: IntArray, n: Int, k: Int): Int {
        val bricksExceptFirstAndLast = bricks.filterIndexed { index, _ -> index in 1 until bricks.lastIndex }
        val result = mutableListOf<List<Int>>()
        combination(bricksExceptFirstAndLast, result, k - 1)
        return result.map { combination -> combination.map { n - it }.sum() }
                .min() ?: 0
    }

    fun combination(elements: List<Int>, result: MutableList<List<Int>>, target: Int,
                    start: Int = 0, visited: BooleanArray = BooleanArray(elements.size)) {
        if (target == 0) {
            result.addAll(listOf(elements.filterIndexed { index, _ -> visited[index] }))
        } else {
            for (i in start until elements.size) {
                visited[i] = true
                combination(elements, result, target - 1, i + 2, visited)
                visited[i] = false
            }
        }
    }
}
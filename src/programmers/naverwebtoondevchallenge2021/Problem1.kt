package programmers.naverwebtoondevchallenge2021

import kotlin.math.max
import kotlin.math.min
import kotlin.test.assertEquals

fun main() {
    assertEquals(4000, Problem1().solution(arrayOf(intArrayOf(60, 50), intArrayOf(60, 30), intArrayOf(80, 40))))
    assertEquals(120, Problem1().solution(arrayOf(intArrayOf(10, 7), intArrayOf(12, 3), intArrayOf(8, 15), intArrayOf(14, 7), intArrayOf(5, 15))))
    assertEquals(133, Problem1().solution(arrayOf(intArrayOf(14, 4), intArrayOf(19, 6), intArrayOf(6, 16), intArrayOf(18, 7), intArrayOf(7, 11))))
    assertEquals(15, Problem1().solution(arrayOf(intArrayOf(5, 5), intArrayOf(1, 1), intArrayOf(2, 2), intArrayOf(3, 3))))
    assertEquals(600, Problem1().solution(arrayOf(intArrayOf(100, 2), intArrayOf(6, 7), intArrayOf(7, 6))))
    assertEquals(600, Problem1().solution(arrayOf(intArrayOf(1, 5), intArrayOf(6, 9), intArrayOf(1, 2), intArrayOf(5, 10), intArrayOf(9, 6))))
}

class Problem1 {

    fun solution(sizes: Array<IntArray>): Int {
        val minNums = mutableListOf<Int>()
        val maxNums = mutableListOf<Int>()
        sizes.forEach {
            minNums.add(min(it[0], it[1]))
            maxNums.add(max(it[0], it[1]))
        }

        return (minNums.max() ?: 1000) * (maxNums.max() ?: 1000)
    }
}
package programmers.naverwebtoondevchallenge2021

import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.test.assertEquals

fun main() {
    assertEquals(2, Problem3().solution(intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)))
    assertEquals(1, Problem3().solution(intArrayOf(0, -1, 0, -2, 8, -2, 0, -2, 0)))
    assertEquals(3, Problem3().solution(intArrayOf(0, -9, -9, -9, 0, -9, -9, 10, -2, -1, -1, -1, -1, -1, 0, -2, -2, -1, -1, -1, 0)))

    assertEquals(2, Problem3().solution(intArrayOf(0, -1, 0, -1, -1, -1, -1, 0)))
    assertEquals(2, Problem3().solution(intArrayOf(0, -1, 0, 3, -1, -1, -1, 0)))
    assertEquals(2, Problem3().solution(intArrayOf(0, -1, 0, 2, 2, -1, -1, 0)))
    assertEquals(2, Problem3().solution(intArrayOf(0, -1, 0, -1, -1, 0, -5, -1, 0)))
    assertEquals(1, Problem3().solution(intArrayOf(0, -1, 5, 5, -1, 0, -5, -1, 5, 0)))
}

class Problem3 {

    fun solution(m: IntArray): Int {
        val initQueue = LinkedList<Int>()
        initQueue.add(0)
        val queues = mutableListOf(initQueue)

        var times = 0
        while (!queues[times].contains(m.lastIndex)) {
            val queue = queues[times]
            val nextQueue = LinkedList<Int>()

            for (position in queue) {
                for (dice in 1..6) {
                    val visited = BooleanArray(m.size)
                    var currentPosition = min(m.lastIndex, max(0, position + dice))
                    var infinite = false

                    while (m[currentPosition] != 0) {
                        visited[currentPosition] = true
                        currentPosition += m[currentPosition]
                        currentPosition = min(m.lastIndex, max(0, currentPosition))

                        if (visited[currentPosition]) {
                            infinite = true
                            break
                        }
                    }

                    if (!infinite) {
                        nextQueue.add(currentPosition)
                    }
                }
            }

            queues.add(nextQueue)
            times++
        }

        return times
    }
}
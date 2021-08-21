package programmers.kakaoblindrecruitment2020

import java.util.*

fun main() {
    MoveBlock().solution(arrayOf(intArrayOf(0, 0, 0, 1, 1),intArrayOf(0, 0, 0, 1, 0),intArrayOf(0, 1, 0, 1, 1),intArrayOf(1, 1, 0, 0, 1),intArrayOf(0, 0, 0, 0, 0)))
}

class MoveBlock {

    companion object {
        private const val UP = 0
        private const val DOWN = 1
        private const val LEFT = 2
        private const val RIGHT = 3
        private val clockwise = arrayOf(RIGHT, LEFT, UP, DOWN)
        private val counterclockwise = arrayOf(LEFT, RIGHT, DOWN, UP)
    }

    private lateinit var board: Array<IntArray>
    private lateinit var visited: Array<Array<BooleanArray>>

    fun solution(board: Array<IntArray>): Int {
        this.board = board
        visited = Array(board.size) { Array(board.size) { BooleanArray(4) { false } } }

        visited[0][0][LEFT] = true
        visited[0][1][RIGHT] = true

        val queues = mutableListOf<LinkedList<Position>>()

        val firstQueue = LinkedList<Position>()
        firstQueue.add(Position(0, 0, LEFT, 0, 1, RIGHT))
        queues.add(firstQueue)

        var times = 0
        while (!visited[visited.lastIndex][visited.lastIndex].contains(true)) {
            val queue = LinkedList<Position>()
            queues.add(queue)

            for (position in queues[times++]) {
                if (canMove(position, UP)) {
                    val newPosition = Position(position.y1 - 1, position.x1, position.direction1,
                            position.y2 - 1, position.x2, position.direction2)
                    queue.add(newPosition)
                }

                if (canMove(position, DOWN)) {
                    val newPosition = Position(position.y1 + 1, position.x1, position.direction1,
                            position.y2 + 1, position.x2, position.direction2)
                    queue.add(newPosition)
                }

                if (canMove(position, LEFT)) {
                    val newPosition = Position(position.y1, position.x1 - 1, position.direction1,
                            position.y2, position.x2 - 1, position.direction2)
                    queue.add(newPosition)
                }

                if (canMove(position, RIGHT)) {
                    val newPosition = Position(position.y1, position.x1 + 1, position.direction1,
                            position.y2, position.x2 + 1, position.direction2)
                    queue.add(newPosition)
                }

                val clockwiseDirection1 = clockwise[position.direction1]
                if (canMove(position, clockwiseDirection1)) {
                    val newPosition = when (clockwiseDirection1) {
                        UP -> Position(position.y2 - 1, position.x2, UP, position.y2, position.x2, DOWN)
                        DOWN -> Position(position.y2, position.x2, UP, position.y2 + 1, position.x2, DOWN)
                        LEFT -> Position(position.y2, position.x2 - 1, LEFT, position.y2, position.x2, RIGHT)
                        RIGHT -> Position(position.y2, position.x2, LEFT, position.y2, position.x2 + 1, RIGHT)
                        else -> Position(position.y1, position.x1, position.direction1, position.y2, position.x2, position.direction2)
                    }
                    queue.add(newPosition)
                }

                val clockwiseDirection2 = clockwise[position.direction2]
                if (canMove(position, clockwiseDirection2)) {
                    val newPosition = when (clockwiseDirection2) {
                        UP -> Position(position.y1 - 1, position.x1, UP, position.y1, position.x1, DOWN)
                        DOWN -> Position(position.y1, position.x1, UP, position.y1 + 1, position.x1, DOWN)
                        LEFT -> Position(position.y1, position.x1 - 1, LEFT, position.y1, position.x1, RIGHT)
                        RIGHT -> Position(position.y1, position.x1, LEFT, position.y1, position.x1 + 1, RIGHT)
                        else -> Position(position.y1, position.x1, position.direction1, position.y2, position.x2, position.direction2)
                    }
                    queue.add(newPosition)
                }

                val counterclockwiseDirection1 = counterclockwise[position.direction1]
                if (canMove(position, counterclockwiseDirection1)) {
                    val newPosition = when (counterclockwiseDirection1) {
                        UP -> Position(position.y2 - 1, position.x2, UP, position.y2, position.x2, DOWN)
                        DOWN -> Position(position.y2, position.x2, UP, position.y2 + 1, position.x2, DOWN)
                        LEFT -> Position(position.y2, position.x2 - 1, LEFT, position.y2, position.x2, RIGHT)
                        RIGHT -> Position(position.y2, position.x2, LEFT, position.y2, position.x2 + 1, RIGHT)
                        else -> Position(position.y1, position.x1, position.direction1, position.y2, position.x2, position.direction2)
                    }
                    queue.add(newPosition)
                }

                val counterclockwiseDirection2 = counterclockwise[position.direction2]
                if (canMove(position, counterclockwiseDirection2)) {
                    val newPosition = when (counterclockwiseDirection2) {
                        UP -> Position(position.y1 - 1, position.x1, UP, position.y1, position.x1, DOWN)
                        DOWN -> Position(position.y1, position.x1, UP, position.y1 + 1, position.x1, DOWN)
                        LEFT -> Position(position.y1, position.x1 - 1, LEFT, position.y1, position.x1, RIGHT)
                        RIGHT -> Position(position.y1, position.x1, LEFT, position.y1, position.x1 + 1, RIGHT)
                        else -> Position(position.y1, position.x1, position.direction1, position.y2, position.x2, position.direction2)
                    }
                    queue.add(newPosition)
                }

                for (addedPosition in queue) {
                    visited[addedPosition.y1][addedPosition.x1][addedPosition.direction1] = true
                    visited[addedPosition.y2][addedPosition.x2][addedPosition.direction2] = true
                }
            }
        }

        return times
    }

    private fun canMove(position: Position, direction: Int): Boolean {
        return when (direction) {
            UP -> position.y1 - 1 >= 0 &&
                    (!visited[position.y1 - 1][position.x1][UP] || !visited[position.y2 - 1][position.x2][DOWN]) &&
                    board[position.y1 - 1][position.x1] == 0 && board[position.y2 - 1][position.x2] == 0
            DOWN -> position.y2 + 1 <= board.lastIndex &&
                    (!visited[position.y1 + 1][position.x1][UP] || !visited[position.y2 + 1][position.x2][DOWN]) &&
                    board[position.y1 + 1][position.x1] == 0 && board[position.y2 + 1][position.x2] == 0
            LEFT -> position.x1 - 1 >= 0 &&
                    (!visited[position.y1][position.x1 - 1][LEFT] || !visited[position.y2][position.x2 - 1][RIGHT]) &&
                    board[position.y1][position.x1 - 1] == 0 && board[position.y2][position.x2 - 1] == 0
            RIGHT -> position.x2 + 1 <= board.lastIndex &&
                    (!visited[position.y1][position.x1 + 1][LEFT] && !visited[position.y2][position.x2 + 1][RIGHT]) &&
                    board[position.y1][position.x1 + 1] == 0 && board[position.y2][position.x2 + 1] == 0
            else -> false
        }
    }

    class Position(val y1: Int, val x1: Int, val direction1: Int, val y2: Int, val x2: Int, val direction2: Int)
}
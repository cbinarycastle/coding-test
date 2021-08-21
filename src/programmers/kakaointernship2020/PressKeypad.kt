package programmers.kakaointernship2020

import kotlin.math.abs

class PressKeypad {

    fun solution(numbers: IntArray, hand: String): String {
        val positionMap: Map<Int, Position> = mapOf(
                1 to Position(0, 0),
                2 to Position(1, 0),
                3 to Position(2, 0),
                4 to Position(0, 1),
                5 to Position(1, 1),
                6 to Position(2, 1),
                7 to Position(0, 2),
                8 to Position(1, 2),
                9 to Position(2, 2),
                0 to Position(1, 3)
        )

        val leftHand = Hand.left(hand == "left", Position(0, 3))
        val rightHand = Hand.right(hand == "right", Position(2, 3))

        return numbers.map {
            val position = positionMap[it]!!
            val closerHand = when (it) {
                in arrayOf(1, 4, 7) -> leftHand
                in arrayOf(3, 6, 9) -> rightHand
                else -> position.let { it1 -> leftHand.closer(rightHand, it1) }
            }

            closerHand.position = position
            closerHand.leftOrRight
        }.reduce { acc, leftOrRight -> acc + leftOrRight }
    }

    class Hand(val leftOrRight: String, val main: Boolean, var position: Position) {

        fun closer(other: Hand, position: Position): Hand {
            val diff = this.position.diff(position)
            val diffWithOther = other.position.diff(position)

            if (diff == diffWithOther) {
                return if (main) this else other
            }

            return if (diff < diffWithOther) this else other
        }

        companion object {
            fun left(main: Boolean, position: Position): Hand {
                return Hand("L", main, position)
            }

            fun right(main: Boolean, position: Position): Hand {
                return Hand("R", main, position)
            }
        }
    }

    class Position(private val x: Int, private val y: Int) {

        fun diff(other: Position): Int {
            return abs((x - other.x)) + abs((y - other.y))
        }
    }
}
package programmers.kakaointernship2019

class Tuple {

    fun solution(s: String): IntArray {
        return s.substring(2 until s.length - 2)
                .split("},{")
                .map { it.split(",") }
                .sortedBy { it.size }
                .reduce { acc, list -> acc.plus(list.first { !acc.contains(it) }) }
                .map { it.toInt() }
                .toIntArray()
    }
}
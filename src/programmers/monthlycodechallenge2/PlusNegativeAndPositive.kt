package programmers.monthlycodechallenge2

class PlusNegativeAndPositive {

    fun solution(absolutes: IntArray, signs: BooleanArray): Int {
        return absolutes.zip(signs.asList()) { absolute, sign -> if (sign) absolute else -absolute }
                .reduce { acc, i -> acc + i }
    }
}
package programmers.summerwintercoding2018

fun main() {
    println(MakePrimeNumber().solution(intArrayOf(1, 2, 7, 6, 4)))
}

class MakePrimeNumber {

    private val primeNumberCheckList = getPrimeNumberCheckList(2997)
    private var numberOfPrimeNumbers = 0

    fun solution(nums: IntArray): Int {
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                dfs(nums, i, j, j + 1)
            }
        }
        return numberOfPrimeNumbers
    }

    private fun dfs(nums: IntArray, index1: Int, index2: Int, index3: Int) {
        if (index3 == nums.size) {
            return
        }

        val num = nums[index1] + nums[index2] + nums[index3]
        if (primeNumberCheckList[num]) {
            numberOfPrimeNumbers++
        }

        dfs(nums, index1, index2, index3 + 1)
    }

    private fun getPrimeNumberCheckList(max: Int): BooleanArray {
        val checkList = BooleanArray(max) { true }

        for (i in 2 until max) {
            for (j in i * i until max step i) {
                checkList[j] = false
            }
        }

        return checkList
    }
}
package programmers.devmatching2021

fun main() {
    MultiLevelToothbrushSales().solution(
            arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"),
            arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"),
            arrayOf("young", "john", "tod", "emily", "mary"),
            intArrayOf(12, 4, 2, 5, 10)
    ).forEach { println(it) }
}

class MultiLevelToothbrushSales {

    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        val graph = mutableMapOf<String, Seller>()
        for (i in enroll.indices) {
            graph[enroll[i]] = Seller(enroll[i], referral[i])
        }

        for (i in seller.indices) {
            var revenue = amount[i] * 100
            var current = graph[seller[i]]
            while (current != null && current.name != "-") {
                val fees = (revenue * 0.1).toInt()
                current.revenue += revenue - fees
                revenue = fees
                current = graph[current.referral]
            }
        }

        return enroll.map { graph[it]?.revenue ?: 0 }
                .toIntArray()
    }

    class Seller(val name: String, val referral: String, var revenue: Int = 0) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Seller

            if (name != other.name) return false

            return true
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }
}
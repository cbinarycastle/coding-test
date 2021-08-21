package programmers.kakaoblindrecruitment2019

class FailureRate {

    fun solution(N: Int, stages: IntArray): IntArray {
        val usersByStages = HashMap<Int, Int>()
        stages.forEach { usersByStages[it] = usersByStages.getOrDefault(it, 0) + 1 }

        var numberOfChallengers = stages.size

        val stagesWithFailureRate = ArrayList<Stage>()
        for (stage in 1..N) {
            val numberOfUsersNotCleared = usersByStages.getOrDefault(stage, 0)
            val failureRate = if (numberOfChallengers > 0) {
                numberOfUsersNotCleared.toDouble() / numberOfChallengers
            } else {
                0.0
            }

            stagesWithFailureRate.add(Stage(stage, failureRate))
            numberOfChallengers -= numberOfUsersNotCleared
        }

        return stagesWithFailureRate.sortedWith(Comparator { o1, o2 ->
            if (o1.failureRate == o2.failureRate) {
                o1.stage.compareTo(o2.stage)
            } else {
                if (o1.failureRate > o2.failureRate) -1 else 1
            }
        })
                .map { it.stage }
                .toIntArray()
    }

    class Stage(val stage: Int, val failureRate: Double)
}
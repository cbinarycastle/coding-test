package util

class Combination {

    fun <T> combination(elements: List<T>, result: MutableList<List<T>>, target: Int,
                start: Int = 0, visited: BooleanArray = BooleanArray(elements.size)) {
        if (target == 0) {
            result.addAll(listOf(elements.filterIndexed { index, _ -> visited[index] }))
        } else {
            for (i in start until elements.size) {
                visited[i] = true
                combination(elements, result, target - 1, i + 1, visited)
                visited[i] = false
            }
        }
    }
}
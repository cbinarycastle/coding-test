package util

class Dijkstra {

    companion object {
        private const val INF = 99999999
    }

    private fun dijkstra(graph: Array<IntArray>, start: Int) {
        val visited = BooleanArray(graph.size)
        visited[start] = true

        for (i in 0 until graph.size - 1) {
            val currentIndex = getNearestIndex(graph[start], visited)
            visited[currentIndex] = true

            graph[currentIndex].forEachIndexed { index, weight ->
                if (weight == 0) {
                    return@forEachIndexed
                }

                if (graph[start][currentIndex] + weight < graph[start][index]) {
                    graph[start][index] = graph[start][currentIndex] + weight
                }
            }
        }
    }

    private fun getNearestIndex(nodes: IntArray, visited: BooleanArray): Int {
        var min = INF
        var minIndex = 0
        nodes.forEachIndexed { index, weight ->
            if (weight in 1 until min && !visited[index]) {
                min = weight
                minIndex = index
            }
        }
        return minIndex
    }
}
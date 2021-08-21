package programmers.kakaoblindrecruitment2019

fun main() {
    CandidateKey().asdf(arrayOf(arrayOf("100", "ryan", "music", "2"), arrayOf("200", "apeach", "math", "2"),
            arrayOf("300", "tube", "computer", "3"), arrayOf("400", "con", "computer", "4"),
            arrayOf("500", "muzi", "music", "3"), arrayOf("600", "apeach", "music", "2")))
}

class CandidateKey {

    fun solution(relation: Array<Array<String>>): Int {
        val columns = Array(relation[0].size) { it }
        val combinationOfColumns = mutableListOf<List<Int>>()

        for (target in 1..relation.size) {
            combination(columns.toList(), combinationOfColumns, target)
        }

        val candidateKeys = mutableListOf<List<Int>>()
        for (combination in combinationOfColumns) {
            var notSatisfiedMinimal = false

            for (keys in candidateKeys) {
                var containsKey = true
                for (key in keys) {
                    if (!combination.contains(key)) {
                        containsKey = false
                        break
                    }
                }
                if (containsKey) {
                    notSatisfiedMinimal = true
                    break
                }
            }

            if (notSatisfiedMinimal) {
                continue
            }

            val identifiers = hashSetOf<String>()
            relation.forEach { row ->
                val values = mutableListOf<String>()
                combination.forEach { column -> values.add(row[column]) }
                val identifier = values.joinToString()
                if (!identifiers.contains(identifier)) {
                    identifiers.add(identifier)
                }
            }

            if (identifiers.size == relation.size) {
                candidateKeys.add(combination)
            }
        }

        return candidateKeys.size
    }

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




    data class Database(val records: List<Record>) {

        fun getUniqueKeys(): Set<Key> {
            val masterKey = Key(List(records.first().columns.size) { it }.toSet())
            return findAllUniqueKeys(masterKey)
        }

        private fun findAllUniqueKeys(key: Key): Set<Key> {
            return if (!definedUniquelyBy(key)) {
                emptySet()
            } else {
                key.subKeys()
                        .flatMap(::findAllUniqueKeys).toSet()
                        .takeIf { it.isNotEmpty() } ?: setOf(key)
            }
        }

        private fun definedUniquelyBy(key: Key): Boolean {
            return records.groupBy { record ->
                // pick only record's columns defined by key
                record.columns.filterIndexed { index, _ -> index in key.columns }
            }.size == records.size
        }
    }

    data class Record(val columns: List<String>)

    data class Key(val columns: Set<Int>) {

        fun subKeys(): List<Key> = columns.map { Key(columns - it) }
    }

    fun asdf(relation: Array<Array<String>>): Int {
        val uniqueKeys = Database(relation.map {
            Record(it.toList())
        }).getUniqueKeys()
        return uniqueKeys.size
    }
}
package util

class Permutation {

    fun <T> permutation(elements: List<T>, result: List<T> = listOf()): List<List<T>> {
        return if (elements.isEmpty()) {
            listOf(result)
        } else {
            elements.flatMap { permutation(elements - it, result + it) }
        }
    }
}
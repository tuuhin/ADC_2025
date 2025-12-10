fun main() {

    fun part1(inputs: List<String>): Long {

        val startPos = inputs[0].indexOfFirst { it == 'S' }
        val startPositions = IntArray(inputs[0].length)
        startPositions[startPos] = 1

        var counter = 0L
        var start = 0

        while (start < inputs.size) {

            val currentRow = inputs[start]

            for ((idx, chr) in currentRow.withIndex()) {
                if (startPositions[idx] == 1 && chr == '^') {
                    counter++
                    startPositions[idx - 1] = 1
                    startPositions[idx + 1] = 1
                    startPositions[idx] = 0
                } else if (startPositions[idx] != 1) startPositions[idx] = 0
            }
            start++
        }
        return counter
    }

    fun calPathCount(
        start: Pair<Int, Int>,
        graph: List<String>,
        cache: MutableMap<Pair<Int, Int>, Long> = mutableMapOf(),
    ): Long {
        val (row, col) = start
        val maxHeight = graph.size

        if (row + 1 == maxHeight) return 1L
        if (cache.containsKey(start)) return cache[start]!!

        val result = if (graph[row + 1][col] == '^') {
            val leftCount = calPathCount(row + 1 to col - 1, graph, cache)
            val rightCount = calPathCount(row + 1 to col + 1, graph, cache)
            leftCount + rightCount
        } else calPathCount(row + 1 to col, graph, cache)
        cache[start] = result
        return result
    }

    fun part2(inputs: List<String>): Long {
        // TAKEN HELP FROM YT VIDEO
        val startPos = inputs[0].indexOfFirst { it == 'S' }
        return calPathCount(0 to startPos, inputs)
    }


    val testInput = readInput("Day07_test")
//    println(part1(testInput) == 21L)
//    println(part2(testInput))

    val input = readInput("Day07")
//    part1(input).println()
//    part2(input).println()
}
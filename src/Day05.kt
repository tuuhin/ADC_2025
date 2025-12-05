fun main() {

    fun part1(inputs: List<String>): Int {
        val blank = inputs.indexOfFirst { it.isEmpty() || it.isBlank() }

        val ranges = inputs.subList(0, blank)
            .map { rangeAsString ->
                val idxOfDash = rangeAsString.indexOfFirst { it == '-' }
                val start = rangeAsString.take(idxOfDash).toLong()
                val end = rangeAsString.substring(idxOfDash + 1).toLong()
                start..end
            }
        val ids = inputs.subList(blank + 1, inputs.size)
            .map { it.toLong() }

        var counter = 0
        for (id in ids) {
            if (ranges.any { ranges -> id in ranges })
                counter++
        }

        return counter
    }

    fun part2(inputs: List<String>): Long {
        val ranges = inputs.takeWhile { it.isNotEmpty() || it.isNotBlank() }

        val rangeOverlapping = ranges.map { rangeAsString ->
            val idxOfDash = rangeAsString.indexOfFirst { it == '-' }
            val start = rangeAsString.take(idxOfDash).toLong()
            val end = rangeAsString.substring(idxOfDash + 1).toLong()
            start..end
        }.sortedBy { it.first }

        val mergedRanges = buildSet {
            var current = rangeOverlapping.first()
            for (i in 1..<rangeOverlapping.size) {
                val next = rangeOverlapping[i]
                if (next.first < current.last) {
                    val maxEnd = maxOf(current.last, next.last)
                    // update the current to the max possible range
                    current = current.first..maxEnd
                } else {
                    add(current)
                    current = next
                }
            }
            // Add the final merged range
            add(current)
        }
        // sum of all the ranges
        return mergedRanges.sumOf { it.last - it.first + 1 }
    }


    val testInput = readInput("Day05_test")
//    check(part1(testInput) == 3)
    println(part2(testInput))

    val input = readInput("Day05")
//    part1(input).println()
    part2(input).println()
}
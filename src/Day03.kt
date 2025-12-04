fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { input ->
            val inputAsList = buildList {
                for (ch in input) add(ch.digitToInt())
            }

            val maxInList = inputAsList.max()
            val indexOfMax = inputAsList.indexOfFirst { element -> element == maxInList }

            // read values of the left
            val finalResult = if (indexOfMax != inputAsList.size - 1) {
                val nextList = inputAsList.subList(indexOfMax + 1, inputAsList.size)
                maxInList * 10 + nextList.max()
            } else {
                // read the right
                val nextList = inputAsList.subList(0, indexOfMax)
                nextList.max() * 10 + maxInList
            }
            println("$input $finalResult")
            finalResult
        }
    }


    fun part2ByGemini(input: String): Long {
        val inputAsList = buildList { for (ch in input) add(ch.digitToInt()) }

        val result = mutableListOf<Int>()
        var start = 0

        for (i in 0..<12) {
            val searchIndex = input.length - 12 + i

            var bestDigit = -1
            var bestIdx = -1

            for (j in start..searchIndex) {
                val curr = inputAsList[j]
                if (curr > bestDigit) {
                    bestDigit = curr
                    bestIdx = j
                    if (bestDigit == 9) break
                }
            }
            result.add(bestDigit)
            start = bestIdx + 1
            print("$bestDigit $start $searchIndex |")
        }
        println()
        return result.fold(0L) { acc, i -> acc * 10 + i }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { input -> 0L }
    }

    val testInput = readInput("Day03_test")
//    check(part1(testInput) == 357)
    println(part2(testInput) == 3121910778619)
//
    val input = readInput("Day03")
//    part1(input).println()
//    part2(input).println()
}

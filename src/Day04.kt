fun main() {

    fun part1(input: List<String>): Long {
        // create the grid
        val yAxisRange = 0..<input.size
        val xAxisRange = 0..<input[0].length

        val positions = buildSet {
            for (yMov in yAxisRange)
                for (xMov in xAxisRange)
                    if (input[yMov][xMov] == '@') add(xMov to yMov)
        }

        var counter = 0L
        positions.forEach { (x, y) ->
            var localCounter = 0
            for (yMov in -1..1) {
                for (xMov in -1..1) {
                    val yPos = y + yMov
                    val xPos = x + xMov
                    if (xPos == x && yPos == y) continue
                    if (xPos !in xAxisRange || yPos !in yAxisRange) continue

                    if (input[yPos][xPos] != '@') continue
                    localCounter++
                }
            }
            if (localCounter < 4) counter++
        }
        return counter
    }


    fun prettyPrint(input: List<String>, invalids: List<Pair<Int, Int>>) {
        for (yMov in 0..<input.size) {
            for (xMov in 0..<input[0].length) {
                val chr = if (xMov to yMov in invalids) '.' else input[yMov][xMov]
                print(" $chr ")
            }
            println()
        }
    }

    fun part2(input: List<String>): Long {
        // create the grid
        val yAxisRange = 0..<input.size
        val xAxisRange = 0..<input[0].length

        val positions = buildSet {
            for (yMov in yAxisRange)
                for (xMov in xAxisRange)
                    if (input[yMov][xMov] == '@') add(xMov to yMov)
        }

        val dispatch = mutableSetOf<Pair<Int, Int>>()

        var counter = 0L
        loop@ while (true) {
            var validDispatch = false
            for ((x, y) in positions) {
                // already dispatched
                if ((x to y) in dispatch) continue

                // same as part1
                var localCounter = 0
                for (yMov in -1..1) {
                    for (xMov in -1..1) {
                        val yPos = y + yMov
                        val xPos = x + xMov
                        if (xPos == x && yPos == y) continue
                        if (xPos !in xAxisRange || yPos !in yAxisRange) continue
                        if (xPos to yPos in dispatch) continue

                        if (input[yPos][xPos] != '@') continue
                        localCounter++
                    }
                }

                if (localCounter < 4) {
                    counter++
                    dispatch.add(x to y)
                    if (!validDispatch) validDispatch = true
                }
            }
            // if its not a valid dispatch stop the work
            if (!validDispatch) break@loop
        }
        return counter
    }

//    val testInput = readInput("Day04_test")
//    check(part1(testInput) == 13L)
//    check(part2(testInput) == 43L)
//
//    val input = readInput("Day04")
//    part1(input).println()
//    part2(input).println()
}

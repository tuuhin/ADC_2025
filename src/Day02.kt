fun main() {

    fun computePart1(start: String, end: String): Set<Long> {
        if (start.length % 2 != 0 && end.length % 2 != 0) return emptySet()
        val finalRange = start.toLong()..end.toLong()
        val startRef = if (start.length % 2 == 0) start else "0$start"
        var mirrorStartLeft = startRef.take(startRef.length / 2)

        return buildSet {
            while (true) {
                if (mirrorStartLeft.startsWith('0')) {
                    mirrorStartLeft = (mirrorStartLeft.toLong() + 1).toString()
                    continue
                }
                // if not in joined
                val joined = "$mirrorStartLeft$mirrorStartLeft".toLong()
                if (joined < finalRange.first) {
                    mirrorStartLeft = (mirrorStartLeft.toLong() + 1).toString()
                    continue
                }

                if (joined > finalRange.last) break
                // if the items exists
                add(joined)
                mirrorStartLeft = (mirrorStartLeft.toLong() + 1).toString()
            }
        }
    }

    fun computePart2(start: String, end: String): Set<Long> {
        val regex = Regex("""(\d+)\1+""")
        val finalRange = start.toLong()..end.toLong()
        return finalRange.toSet().filter { regex.matches(it.toString()) }
            .toSet()
    }

    fun part1(input: List<String>): Long {
        return input.flatMap { it.split(",") }
            .filterNot { it.isBlank() }
            .flatMap {
                val numberSplit = it.split("-")
                computePart1(numberSplit[0], numberSplit[1])
            }.sum()
    }

    fun part2(input: List<String>): Long {
        return input.flatMap { it.split(",") }
            .filterNot { it.isBlank() }
            .flatMap {
                val numberSplit = it.split("-")
                val start = numberSplit[0]
                val end = numberSplit[1]
                val result = computePart2(start, end)
                println("$start $end = $result")
                result
            }.sum()
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 4174379265L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

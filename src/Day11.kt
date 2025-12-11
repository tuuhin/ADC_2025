fun main() {

    fun parseInput(inputs: List<String>) =
        inputs.map { inp -> inp.split(":").let { str -> str[0] to str[1].trim().split(" ").toList() } }

    fun calculate1(inputs: List<Pair<String, List<String>>>, start: String, acc: Int = 0): Int {
        val row = inputs.firstOrNull { (first, _) -> first == start } ?: return acc
        if (row.second.contains("out")) return acc + 1
        return row.second.sumOf { str -> calculate1(inputs, str, acc) }
    }

    data class CacheKey(
        val start: String,
        val dacSeen: Boolean,
        val fftSeen: Boolean,
    )

    fun calculate2(
        inputs: List<Pair<String, List<String>>>,
        start: String,
        dacSeen: Boolean = false,
        fftSeen: Boolean = false,
        visited: List<String> = mutableListOf(),
        cache: MutableMap<CacheKey, Long> = mutableMapOf(),
    ): Long {
        // TAKEN HELP TO ADD CACHING
        val key = CacheKey(start, dacSeen, fftSeen)
        if (cache.containsKey(key)) {
            val re = cache.getValue(key)
            return re
        }

        if (start in visited) return 0L

        val row = inputs.firstOrNull { (first, _) -> first == start } ?: return 0L

        if (row.second.contains("out")) {
            val result = if (dacSeen && fftSeen) 1L else 0L
            return result
        }

        val result = row.second.sumOf { str ->
            val containsDAC = dacSeen || str == "dac"
            val containsFFT = fftSeen || str == "fft"
            calculate2(inputs, str, containsDAC, containsFFT, visited + start, cache)
        }
        cache[key] = result

        return result
    }

    fun part1(inputs: List<String>): Int {
        return calculate1(parseInput(inputs), "you")
    }

    fun part2(inputs: List<String>): Long {
        return calculate2(parseInput(inputs), "svr")
    }

    val testInput01 = readInput("Day11_test")
    check(part1(testInput01) == 5)

    val testInput02 = readInput("Day11_test_02")
    check(part2(testInput02) == 2L)

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}
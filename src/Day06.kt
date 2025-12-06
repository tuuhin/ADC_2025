fun main() {

    fun performOperations(numbers: List<Long>, op: List<Char>): Long {
        if (numbers.isEmpty()) return 0L
        var total = 0L

        repeat(op.size) { opIdx ->
            val op = op[opIdx]
            val start = if (op == '*') 1L else 0L
            total += numbers.fold(start) { acc, i -> if (op == '*') acc * i else acc + i }
        }
        println(total)
        return total
    }

    fun part1(inputs: List<String>): Long {
        val opStartIdx = inputs.indexOfFirst { rowEntry ->
            rowEntry.split(" ").all { it.toIntOrNull() == null }
        }

        val baseLineSize = inputs[0].length

        // operands
        val operandsList = inputs.take(opStartIdx)
        val operands = buildList {
            var idx = 0
            var start = 0
            var lastSepPos = -1
            while (idx < baseLineSize) {
                val columnSep = operandsList.all { numberLine -> numberLine[idx] == ' ' }
                if (!columnSep) {
                    idx++
                    continue
                }
                lastSepPos = idx
                val subStrings = operandsList.map { numberLine ->
                    numberLine.substring(start, idx)
                        .trim()
                        .toLong()
                }
                add(subStrings)
                start = idx + 1
                idx++
            }
            if (lastSepPos != -1) {
                val subStrings = operandsList.map { numberLine ->
                    numberLine.substring(lastSepPos).trim().toLong()
                }
                add(subStrings)
            }
        }

        // operations
        val operationsList = inputs.subList(opStartIdx, inputs.size)
            .map { opLine -> opLine.filterNot { it == ' ' } }

        val operation = buildList {
            var idx = 0
            while (idx < operationsList[0].length) {
                add(operationsList.map { ops -> ops[idx] })
                idx++
            }
        }
        var counter = 0L
        repeat(operation.size) {
            counter += performOperations(operands[it], operation[it])
        }

        return counter
    }

    fun part2(inputs: List<String>): Long {
        val opStartIdx = inputs.indexOfFirst { rowEntry ->
            rowEntry.split(" ").all { it.toIntOrNull() == null }
        }
        val baseLineSize = inputs[0].length

        // operands
        val operandsList = inputs.take(opStartIdx)
        val operands = buildList {
            var idx = 0
            var start = 0
            var lastSepPos = -1
            while (idx < baseLineSize) {
                val columnSep = operandsList.all { numberLine -> numberLine[idx] == ' ' }
                if (!columnSep) {
                    idx++
                    continue
                }
                lastSepPos = idx
                val subStrings = operandsList.map { numberLine ->
                    numberLine.substring(start, idx)
                }
                add(subStrings)
                start = idx + 1
                idx++
            }
            if (lastSepPos != -1) {
                val subStrings = operandsList.map { numberLine ->
                    numberLine.substring(lastSepPos)
                }
                add(subStrings)
            }
        }.map { list ->
            val maxLength = list.maxOf { it.length }
            val final = buildList {
                var sum: Long
                for (idx in 0..<maxLength) {
                    sum = 0
                    for (liIdx in 0..<list.size) {
                        if (idx >= list[liIdx].length) continue
                        val chr = list[liIdx][idx]
                        if (chr == ' ') continue
                        val number = chr.digitToInt()
                        sum = sum * 10L + number
                    }
                    add(sum)
                }
            }
            final
        }
        // operations
        val operationsList = inputs.subList(opStartIdx, inputs.size)
            .map { opLine -> opLine.filterNot { it == ' ' } }


        val operation = buildList {
            var idx = 0
            while (idx < operationsList[0].length) {
                add(operationsList.map { ops -> ops[idx] })
                idx++
            }
        }
        var counter = 0L
        repeat(operation.size) {
            counter += performOperations(operands[it], operation[it])
        }
        return counter
    }


    val testInput = readInput("Day06_test")
    check(part1(testInput) == 4277556L)
    println(part2(testInput) == 3263827L)

    val input = readInput("Day06")
//    part1(input).println()
//    part2(input).println()
}
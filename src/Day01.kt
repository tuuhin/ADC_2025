fun main() {
    fun part1(input: List<String>): Int {
        var position = 50
        var counter = 0
        for (i in input) {
            val isLeft = i.first() == 'L'
            val movement = i.substring(1).toInt()

            if (isLeft) {
                if (position - movement <= 0) {
                    val netPosition = 100 - (movement - position)
                    position = netPosition % 100
                } else position -= movement
            } else {
                if (movement + position >= 100) {
                    val netPosition = movement + position - 100
                    position = netPosition % 100
                } else position += movement
            }
            // it ends in 100 or 0 it's a click
            if (position == 100 || position == 0) {
                counter++
                position = 0
            }
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        var position = 50
        var counter = 0
        for (i in input) {
            val isLeft = i.first() == 'L'
            val movement = i.substring(1).toInt()
            // reduce the counter value
            val reducedMovement = movement % 100
            var loopCount = movement / 100

            if (isLeft) {
                if (position - reducedMovement <= 0) {
                    if (position != 0) loopCount += 1
                    position = 100 - (reducedMovement - position)
                    if (position == 100) position = 0
                } else {
                    position -= reducedMovement
                }
            } else {
                if (reducedMovement + position >= 100) {
                    loopCount += 1
                    position = (reducedMovement + position) - 100
                } else {
                    position += reducedMovement
                }
            }
            counter += loopCount
        }
        return counter
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)


    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

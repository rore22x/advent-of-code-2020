package de.romrei.aoc

object Day9 {

    fun part1(input: String, lenPreamble: Int = 25) {
        val values = parse(input)
        var iter = lenPreamble
        while (true) {
            val current = values[iter]
            val filtered = values.subList(iter - lenPreamble, iter).sortedBy { it }.toList()
            if (!isValidSequence(filtered, current)) {
                println("Result $current (index: $iter)")
                break
            }
            iter++
        }
    }

    private fun isValidSequence(filtered: List<Long>, current: Long): Boolean {
        for ((i, arg1) in filtered.withIndex()) {
            for ((j, arg2) in filtered.withIndex()) {
                if (i == j) {
                    continue
                }
                if (arg1 + arg2 == current) {
                    return true
                }
            }
        }
        return false
    }

    fun part2(input: String, beginIndex: Int) {
        val values = parse(input)
        findSequence(parse(input).subList(0, beginIndex), values[beginIndex])
    }

    private fun findSequence(values: List<Long>, target: Long): Boolean {
        for ((index, value) in values.withIndex()) {
            if (value >= target) {
                continue
            }
            val result = findSequence(values, target, index, value)
            if (result) {
                return result
            }
        }
        return false
    }

    private fun findSequence(values: List<Long>, target: Long, startIndex: Int, startValue: Long): Boolean {
        var nextIndex = startIndex + 1
        var summe = startValue
        val allValues = mutableListOf(startValue)
        while (true) {
            if (summe + values[nextIndex] == target) {
                val min = allValues.minBy { it } ?: 0L
                val max = allValues.maxBy { it } ?: 0L
                println("Result $min + $max = ${min + max}")
                return true
            } else if (summe + values[nextIndex] > target) {
                return false
            } else {
                allValues.add(values[nextIndex])
                summe += values[nextIndex]
            }
            nextIndex++
        }
    }

    private fun parse(input: String): List<Long> {
        return input.lines().filter { it.isNotEmpty() }.map { it.toLong() }.toList()
    }
}
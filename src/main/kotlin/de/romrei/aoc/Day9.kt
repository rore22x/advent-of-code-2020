package de.romrei.aoc

object Day9 {

    fun part1(input: String) {
        val lenPreamble = 25
        val ints = parse(input)
        var iter = lenPreamble
        while (true) {
            val current = ints[iter]
            val filtered = ints.subList(iter - lenPreamble, iter).sortedBy { it }.toList()
            if (!findArgs(filtered, current)) {
                println("Result $current")
                break
            }
            iter++
        }
    }

    private fun findArgs(filtered: List<Long>, current: Long): Boolean {
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
        val ints = parse(input)
        val current = ints[beginIndex]
        val filtered = ints.subList(0, beginIndex)
        sequential(filtered, current)
    }

    private fun sequential(values: List<Long>, target: Long): Boolean {
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
                println("result $min +  ${max} = ${min + max}")
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

    fun parse(input: String): List<Long> {
        return input.lines().filter { it.isNotEmpty() }.map { it.toLong() }.toList()
    }
}
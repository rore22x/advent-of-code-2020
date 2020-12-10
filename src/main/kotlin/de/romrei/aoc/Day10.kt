package de.romrei.aoc

import kotlin.math.pow

object Day10 {

    fun part1(input: String) {
        val adapters = parse(input).toMutableList()
        adapters.add(0)
        val sorted = adapters.sortedBy { it }.toMutableList()
        sorted.add(sorted[sorted.size - 1] + 3)

        var ones = 0L
        var threes = 0L

        for ((index, data) in sorted.withIndex()) {
            if (index + 1 >= sorted.size) {
                break
            }
            val delta = sorted[index + 1] - data
            if (delta == 3) {
                threes++
            } else if (delta == 1) {
                ones++
            }
        }
        println("Result: ${ones * threes}")
    }

    fun part2count(input: String) {
        val adapters = parse(input).toMutableList()
        adapters.add(0)
        val sorted = adapters.sortedBy { it }.toMutableList()
        sorted.add(sorted[sorted.size - 1] + 3)
        val seqs = IntArray(6)
        var seq = 1
        var j = 0
        while (j <= sorted.size - 2) {
            if (sorted[j + 1] - sorted[j] == 1) {
                seq++
            } else {
                seqs[seq]++
                seq = 1
            }
            j++
        }
        seqs[seq]++

        var ans: Long = 1
        ans *= 2.0.pow(seqs[3].toDouble()).toLong()
        ans *= 4.0.pow(seqs[4].toDouble()).toLong()
        ans *= 7.0.pow(seqs[5].toDouble()).toLong()
        println("Result: $ans")
    }

    private fun parse(input: String): List<Int> {
        return input.lines()
                .filter { it.isNotEmpty() }.map { it.toInt() }.toList()
    }
}
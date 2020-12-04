package de.romrei.aoc

import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths


internal class Day4Test {

    @Test
    fun exec_part1() {
        val day = Day4()
        day.part1(Day4.parse(getInput()))
    }

    @Test
    fun exec_part2() {
        val day = Day4()
        day.part2(Day4.parse(getInput()))
    }

    private fun getInput(): String {
        val sb = StringBuilder()
        Files.readAllLines(Paths.get("./data/day4.txt")).forEach { sb.append(it).append("\n") }
        return sb.toString()
    }
}
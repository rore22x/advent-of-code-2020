package de.romrei.aoc

import org.junit.Test

internal class Day9Test {

    @Test
    fun exec_part1() {
        Day9.part1(Common.getInput(9, false))
    }

    @Test
    fun exec_part2() {
        // index 508 from part1
        Day9.part2(Common.getInput(9, false), 508)
    }
}

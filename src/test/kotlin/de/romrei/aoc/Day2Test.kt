package de.romrei.aoc

import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day2Test {

    @Test
    fun exec_part1() {
        val part1 = Day2Part1()
        part1.execute(getInput())
    }

    @Test
    fun exec_part2() {
        val part2 = Day2Part2()
        part2.execute(getInput())
    }


    fun getSample(): List<Day2Input> {
        val ser = "1-3 a: abcde\n" +
                "1-3 b: cdefg\n" +
                "2-9 c: ccccccccc"
        val input = mutableListOf<Day2Input>()
        ser.split("\n").forEach { input.add(Day2Input.parse(it)) }
        return input
    }

    fun getInput(): List<Day2Input> {
        val lines = Files.readAllLines(Paths.get("./data/day2.txt"))
        return lines.map { Day2Input.parse(it) }.toList()
    }

}
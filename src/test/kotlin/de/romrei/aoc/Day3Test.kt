package de.romrei.aoc

import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths


internal class Day3Test {

    @Test
    fun exec_part1() {
        val day = Day3.Part1()
        day.execute(Day3.Area.parse(getInput()))
    }

    @Test
    fun exec_part2() {
        val day = Day3.Part2()
        day.execute(Day3.Area.parse(getInput()))
    }

    private fun getInput(): String {
        val sb = StringBuilder()
        Files.readAllLines(Paths.get("./data/day3.txt"))
                .forEach { sb.append(it).append("\n") }
        return sb.toString()
    }

    private fun getSample(): String {
        return "..##.........##.........##.........##.........##.........##.......\n" +
                "#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..\n" +
                ".#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.\n" +
                "..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#\n" +
                ".#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.\n" +
                "..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....\n" +
                ".#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#\n" +
                ".#........#.#........#.#........#.#........#.#........#.#........#\n" +
                "#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...\n" +
                "#...##....##...##....##...##....##...##....##...##....##...##....#\n" +
                ".#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#"
    }
}
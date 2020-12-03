package de.romrei.aoc

/**
 * https://adventofcode.com/2020/day/3
 */
class Day3 {

    class Part1 {

        fun execute(area: Area) {
            println("Trees: ${countTrees(area, Slope(1, 3))}")
        }

    }

    class Part2 {

        fun execute(area: Area) {
            val slopes = listOf(Slope(1, 1), Slope(1, 3), Slope(1, 5), Slope(1, 7), Slope(2, 1))
            var multiply = 1L
            for (slope in slopes) {
                val trees = countTrees(area, slope)
                multiply *= trees
            }
            println("Multiplied Trees: $multiply")
        }

    }

    companion object {

        fun countTrees(area: Area, slope: Slope): Long {
            var i = slope.i
            var j = slope.j
            var trees = 0L
            while (true) {
                val result = area[i, j]
                if (result == null) {
                    break
                } else if (result) {
                    trees++
                }
                i += slope.i
                j += slope.j
            }
            return trees
        }

    }

    class Slope(val i: Int, val j: Int)

    class Area(private val matrix: List<List<Boolean>>) {

        private fun getValue(i: Int, j: Int): Boolean? {
            if (matrix.size > i) {
                return matrix[i][j % matrix[0].size]
            }
            return null
        }

        operator fun get(i: Int, j: Int): Boolean? {
            return getValue(i, j)
        }

        companion object {

            fun parse(str: String): Area {
                val list = mutableListOf<List<Boolean>>()
                for (line in str.split("\n")) {
                    if (line == "")
                        continue
                    list.add(line.toCharArray().map { it == '#' }.toMutableList())
                }
                return Area(list)
            }
        }
    }
}
package de.romrei.aoc

/**
 * https://adventofcode.com/2020/day/2
 */
class Day2Part1 {

    fun execute(input: List<Day2Input>) {
        var valid = 0
        for (row in input) {
            if (row.policy.check(row.pwd)) {
                valid++
            }
        }
        println("Valid: $valid")
    }

}

class Day2Part2 {

    fun execute(input: List<Day2Input>) {
        var valid = 0
        for (row in input) {
            if (row.policy.check2(row.pwd)) {
                valid++
            }
        }
        println("Valid: $valid")
    }

}

class Day2Policy(val min: Int, val max: Int, val letter: Char) {

    fun check(pwd: String): Boolean {
        var count = 0L
        for (c in pwd.toCharArray()) {
            if (c == letter) {
                count++
            }
        }
        return count in min..max
    }

    fun check2(pwd: String): Boolean {
        val first = pwd[min - 1] == letter
        val second = pwd[max - 1] == letter
        return (first && !second) || (!first && second)
    }

    override fun toString(): String {
        return "Policy(min=$min, max=$max, letter=$letter)"
    }
}

class Day2Input(val policy: Day2Policy, val pwd: String) {

    companion object {

        fun parse(str: String): Day2Input {
            val pro = str.replace(": ", ":")
            val splitFirst = pro.split("-")
            val min = splitFirst[0].toInt()

            val splitSecond = splitFirst[1].split(" ")
            val max = splitSecond[0].toInt()

            val splitThird = splitSecond[1].split(":")
            val letter = splitThird[0][0]

            val pwd = splitThird[1]
            return Day2Input(Day2Policy(min, max, letter), pwd)
        }
    }

    override fun toString(): String {
        return "Input(policy=$policy, pwd='$pwd')"
    }
}
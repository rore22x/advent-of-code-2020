package de.romrei.aoc

object Day6 {

    fun part1(input: String){
        println("Result: ${input.split("\n\n").sumBy{ countGroup(it) }}")
    }

    private fun countGroup(group: String): Int {
        return group.toCharArray().toSet().filter { it != '\n' }.count()
    }

    fun part2(input: String){
        println("Result: ${input.split("\n\n").sumBy {countGroupEvery(it) }}")
    }

    private fun countGroupEvery(group: String): Int {
        val persons = group.split("\n").filter { it != "" }.toList()
        return persons[0].toCharArray()
                .filter { answer -> persons.count { person -> person.toCharArray().contains(answer) } == persons.size }
                .count()
    }
}
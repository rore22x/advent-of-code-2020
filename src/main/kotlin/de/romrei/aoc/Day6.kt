package de.romrei.aoc

object Day6 {

    fun part1(input: String){
        val result = input.split("\n\n")
                .map {countGroup(it) }
                .sum()
        println("Result: $result")
    }

    private fun countGroup(group: String): Int {
        return group.toCharArray().toSet().filter { it != '\n' }.count()
    }

    fun part2(input: String){
        val result = input.split("\n\n")
                .map {countGroupEvery(it) }
                .sum()
        println("Result: $result")
    }

    private fun countGroupEvery(group: String): Int {
        val persons = group.split("\n").filter { it != "" }.toList()
        val init = persons[0]
        var count = 0
        for(char in init.toCharArray()){
            if(persons.count { it.toCharArray().contains(char) } == persons.size){
                count++
            }
        }
        return count
    }
}
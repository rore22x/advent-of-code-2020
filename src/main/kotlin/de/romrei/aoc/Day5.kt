package de.romrei.aoc

import kotlin.math.ceil

object Day5 {

    fun part1(input: List<String>){
        println("Result: ${interpretTickets(input).max()}")
    }

    fun part2(input: List<String>){
        val ids = interpretTickets(input)
        for(id in 0..(ids.max()?:0)){
            if(!ids.contains(id) && ids.contains(id - 1) && ids.contains(id + 1)){
                println("Result: $id")
            }
        }
    }

    private fun interpretTickets(input: List<String>): MutableSet<Int> {
        val seats = mutableSetOf<Int>()
        for (ticket in input) {
            if (ticket.length != 10) {
                continue
            }
            val id = getRow(ticket) * 8 + getSeat(ticket)
            seats.add(id)
        }
        return seats
    }

    private fun interpret(ticket: String, end: Int, range: IntRange, lower: Char): Int {
        var startC = 0
        var endC = end
        for (i in range) {
            val pos = ticket[i] == lower
            val diff = (endC - startC) / 2.0
            if (pos) {
                endC -= ceil(diff).toInt()
            } else {
                startC += ceil(diff).toInt()
            }
        }
        return startC.coerceAtMost(endC)
    }

    private fun getSeat(ticket: String): Int = interpret(ticket, 7, IntRange(7, 9), 'L')

    private fun getRow(ticket: String): Int = interpret(ticket, 127, IntRange(0, 6), 'F')

}
package de.romrei.aoc

import java.lang.IllegalStateException
import java.lang.Math.abs

object Day12 {

    private val dirs = mapOf(
            Pair("E", Coord(1,0)),
            Pair("S", Coord(0,-1)),
            Pair("W", Coord(-1,0)),
            Pair("N", Coord(0,1))
    )
    private val navs = listOf("E", "S", "W", "N")

    fun part1(input: String){
        val navigations = parse(input)
        var position = Coord(0, 0)
        var currIndex = 0
        for(navigation in navigations){
            when (navigation.action) {
                "L", "R" -> currIndex = turn(currIndex, navigation.action, navigation.value)
                "F" -> position = position.move(navigation.value, dirs[navs[currIndex]]!!)
                else -> position = position.move(navigation.value, dirs[navigation.action]!!)
            }
        }
        println("Result: ${abs(position.x) + abs(position.y)}")
    }

    private fun turn(current: Int, direction: String, degree: Int): Int{
        var index = current + (if(direction == "R") 1 else -1) * (degree / 90)
        if(index < 0){
            index += 4
        }
        return index % 4
    }

    fun part2(input: String){
        val navigations = parse(input)
        var position = Coord(0, 0)
        var waypoint = Coord(10, 1)
        for(navigation in navigations){
            when (navigation.action) {
                "L", "R" -> waypoint = rotate(navigation, waypoint)
                "F" -> position = position.move(navigation.value, waypoint)
                else -> waypoint = waypoint.move(navigation.value, dirs[navigation.action]!!)
            }
        }
        println("Result: ${abs(position.x) + abs(position.y)}")
    }

    private fun rotate(instr: Instr, coord: Coord): Coord{
        var current = coord
        for(step in 0 until (if(instr.action == "L") 360 - instr.value else instr.value) / 90){
            current = current.rotateRight()
        }
        return current
    }

    class Coord(val x: Int, val y: Int){

        fun move(value: Int, direction: Coord): Coord {
            return Coord(x + value * direction.x,
            y + value * direction.y)
        }

        fun rotateRight(): Coord{
            return Coord(y, -x)
        }

    }

    private class Instr(val action: String, val value: Int)

    private fun parse(input: String): List<Instr>{
        return input.lines().filter { it.isNotEmpty() }.map { Instr(it.substring(IntRange(0,0)), it.replace(Regex("[a-zA-Z]"), "").toInt()) }
    }
}
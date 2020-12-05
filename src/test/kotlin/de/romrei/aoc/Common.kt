package de.romrei.aoc

import java.nio.file.Files
import java.nio.file.Paths

object Common {

    fun getInput(day: Int, sample: Boolean = false): String {
        val sb = StringBuilder()
        val suffix = if(sample){
            "sample.txt"
        } else {
            ".txt"
        }
        Files.readAllLines(Paths.get("./data/day$day$suffix")).forEach { sb.append(it).append("\n") }
        return sb.toString()
    }

}
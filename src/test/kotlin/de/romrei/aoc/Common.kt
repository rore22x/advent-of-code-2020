package de.romrei.aoc

import java.nio.file.Files
import java.nio.file.Paths

object Common {

    fun getInput(day: Int, sample: Boolean = false): String {
        val suffix = if(sample){
            "sample.txt"
        } else {
            ".txt"
        }
        val file = "./data/day$day$suffix"
        return read(file)
    }

    fun read(file: String): String {
        val sb = StringBuilder()
        Files.readAllLines(Paths.get(file)).forEach { sb.append(it).append("\n") }
        return sb.toString()
    }

}
package de.romrei.aoc

import java.util.*

object Day7 {

    fun part1(input: String){
        val list = parse(input)
        val map = list.map { it.color to it.children}.toMap()

        val containsGold = mutableSetOf<String>()
        for (def in list) {
            if(containsGold(def, map)){
                containsGold.add(def.color)
            }
        }
        println("Result: ${containsGold.count()}")
    }

    private fun containsGold(def: Def, map: Map<String, List<Tuple>>): Boolean{
        val queue = LinkedList<String>()
        queue.add(def.color)
        while(queue.isNotEmpty()){
            val current = queue.remove()
            val children = map[current]
            if(children != null && children.isNotEmpty()){
                queue.addAll(children.map { it.color }.toList())
                if(children.any{it.color.contains("shiny gold")}){
                    return true
                }
            }
        }
        return false
    }

    fun part2(input: String){
        val list = parse(input)
        val map = list.map { it.color to it.children}.toMap()
        for (def in list) {
            if(def.color == "shiny gold"){
                println("Result: ${countBags(def, map)}")
                break
            }
        }
    }

    private fun countBags(def: Def, map: Map<String, List<Tuple>>): Int {
        val queue = LinkedList<Tuple>()
        queue.add(Tuple(1, def.color))
        var count = 0
        while(queue.isNotEmpty()){
            val current = queue.remove()
            val children = map[current.color]
            if(children != null && children.isNotEmpty()){
                queue.addAll(children.map { Tuple(it.number, it.color, current.multiFactor * it.number) }.toList())
                count += (current.multiFactor * children.sumBy {  it.number })
            }
        }
        return count
    }


    private fun parse(input: String): List<Def>{
        val result = mutableListOf<Def>()
        for (line in input.lines()) {
            val color = line.replace(Regex("bags contain.*"), "").trim()
            if(line.contains("contain no other bags.")){
                result.add(Def(color, listOf()))
            } else {
                val childs = Regex("[0-9]+[a-zA-Z ]*bag[s]?[,.]")
                        .findAll(line)
                        .map { Tuple(it.value.replaceAfter(" ", "").trim().toInt(), it.value.replace(Regex("[0-9]+"), "").replace(Regex("bag[s]?[,.]"), "").trim()) }
                        .toList()
                result.add(Def(color, childs))
            }
        }
        return result
    }

    class Def(val color: String, val  children: List<Tuple>)

    class Tuple(val number: Int, val color: String, val multiFactor: Int = 1)

}
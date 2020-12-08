package de.romrei.aoc

object Day8 {


    fun part1(input: String) {
        val acc = Acc()
        val instructions = parse(input)
        var nextIndex = 0
        var instr = instructions[nextIndex]
        while (instr.lastExecution < 0) {
            instr.lastExecution = nextIndex
            nextIndex = incrementer(instr, nextIndex, acc)
            instr = instructions[nextIndex]
        }
        println("Result: ${acc.count}")
    }

    fun part2(input: String) {
        val acc = Acc()
        val instructions = parse(input)
        var nextIndex = 0
        var currentReplaced: Instr? = null
        while (nextIndex < instructions.size) {
            val instr = instructions[nextIndex]
            if (instr.lastExecution > 0) {
                instructions.forEach { it.lastExecution = -1 }
                currentReplaced?.setBack()
                currentReplaced = instructions.find { it.replaceCandidate() }
                currentReplaced?.switchReplaced()
                acc.count = 0
                nextIndex = 0
                continue
            }
            instr.lastExecution = nextIndex
            nextIndex = incrementer(instr, nextIndex, acc)
        }
        println("Result: ${acc.count}")
    }

    private fun incrementer(instr: Instr, nextIndex: Int, acc: Acc): Int {
        var nextIndex1 = nextIndex
        when (instr.getCommand()) {
            Command.NOP -> nextIndex1++
            Command.JMP -> nextIndex1 += instr.number
            Command.ACC -> {
                acc.count += instr.number
                nextIndex1++
            }
        }
        return nextIndex1
    }

    private fun parse(input: String): List<Instr> {
        val instructions = mutableListOf<Instr>()
        for (line in input.lines()) {
            if (line.isEmpty()) continue
            val command = when (line.substring(IntRange(0, 2))) {
                "acc" -> Command.ACC
                "jmp" -> Command.JMP
                "nop" -> Command.NOP
                else -> null
            }
            val number = Regex("[+-][0-9]+").find(line)?.value?.toInt()
            if (number != null && command != null) {
                instructions.add(Instr(command, number))
            }
        }
        return instructions
    }

    class Instr(private val command: Command, val number: Int) {
        var lastExecution = -1
        var replaced: Command? = null
        fun getCommand(): Command {
            if (replaced != null) {
                return replaced!!
            }
            return command
        }

        fun setBack() {
            replaced = command
        }

        fun replaceCandidate(): Boolean {
            return replaced == null && (command == Command.NOP || command == Command.JMP)
        }

        fun switchReplaced() {
            if (replaced != null) {
                throw IllegalStateException("failure")
            }
            replaced = when (command) {
                Command.NOP -> Command.JMP
                Command.JMP -> Command.NOP
                Command.ACC -> Command.ACC
            }
        }
    }

    enum class Command {
        NOP,
        JMP,
        ACC
    }

    class Acc() {
        var count = 0
    }
}